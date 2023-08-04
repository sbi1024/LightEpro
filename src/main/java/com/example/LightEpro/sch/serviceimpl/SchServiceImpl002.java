package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch002.SchRsDto002;
import com.example.LightEpro.sch.mapper.SchMapper002;
import com.example.LightEpro.sch.service.SchService002;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl002 implements SchService002 {
    private final SchMapper002 schMapper002;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto002 modifySingleSch(SchRqDto002 schRqDto002) throws Exception {
        log.info("modifySingleSch Method Start !!!");
        log.info("modifySingleSch Method Request Data : " + schRqDto002);

        // 해당 일정에 대한 t_sc_sch_user 테이블 데이터 삭제
        int deleteSchUsersCnt = deleteSchUsers(schRqDto002);
        // 일정 시퀀스 번호 주입 메소드 호출
        int curSeq = assignObject(schRqDto002);
        // 단일 일정 수정 메소드 호출
        int updateSchCnt = updateSingleSch(schRqDto002);
        // 일정 참여자 등록 메소드 호출
        int participantsInsertCnt = insertSchParticipants(schRqDto002);
        // 일정 공개범위 등록 메소드 호출
        int disclosureScopesInsertCnt = insertSchDisclosureScopes(schRqDto002);

        // 단일 일정 수정시 , 참여자에 본인이 포함되어 있는지 확인
        int checkRegistrantCnt = schMapper002.checkRegistrant(curSeq);
        // 참여자에 본인이 포함되어 있지 않다면 , 본인을 참여자로 등록 진행
        // 이때 기존의 일정 데이터를 기반으로 등록자를 확인하여 등록 진행한다.
        if (checkRegistrantCnt == 0) {
            log.info("modifySingleSch Method checkRegistrantCnt = 0");
            int insertRegistCnt = schMapper002.insertSchParticipant(curSeq,
                    schRqDto002.getCalendar().getCalSeq(),
                    schRqDto002.getSch().getCreateSeq(),
                    schRqDto002.getSch().getCreateDate(),
                    schRqDto002.getEmp().getEmpSeq());
            // cnt + 1 진행
            participantsInsertCnt += insertRegistCnt;
        }

        // SCH_RS_DTO_002  객체 생성
        SchRsDto002 schRsDto002 = SchRsDto002.builder()
                .schmSeq(curSeq)
                .schSeq(curSeq)
                .deleteSchCnt(deleteSchUsersCnt)
                .updateSchCnt(updateSchCnt)
                .participantsInsertCnt(participantsInsertCnt)
                .disclosureScopesInsertCnt(disclosureScopesInsertCnt)
                .build();

        log.info("modifySingleSch Method Return Data : " + schRsDto002);
        log.info("modifySingleSch Method End !!!");
        // return
        return schRsDto002;
    }

    // 일정 참여자 데이터 전체 삭제
    @Override
    public int deleteSchUsers(SchRqDto002 schRqDto002) throws Exception {
        log.info("deleteSchUsers Method Start !!!");
        log.info("deleteSchUsers Method Request Data : " + schRqDto002);

        int deleteSchUsersCnt = schMapper002.deleteSchUsers(schRqDto002);

        log.info("deleteSchUsers Method Return Data : " + deleteSchUsersCnt);
        log.info("deleteSchUsers Method End !!!");
        // return
        return deleteSchUsersCnt;
    }

    // 일정 시퀀스 번호 주입 메소드
    @Override
    public int assignObject(SchRqDto002 schRqDto002) throws Exception {
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto002);

        // schRqDto002 객체로 부터 내부 클래스 객체 생성
        SchRqDto002.Emp emp = schRqDto002.getEmp();
        SchRqDto002.Sch sch = schRqDto002.getSch();
        SchRqDto002.Calendar calendar = schRqDto002.getCalendar();
        List<SchRqDto002.Participant> participants = schRqDto002.getParticipants();
        List<SchRqDto002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();

        // 일정 시퀀스 값 할당
        int curSeq = sch.getSchmSeq();
        // 본래 일정의 createSeq , createDate 값을 추출
        SchRqDto002.Sch createSeqBySchmSeqAndSchSeq = schMapper002.findCreateSeqBySchmSeqAndSchSeq(schRqDto002);
        // originCreateSeq 추출
        int originSchCreateSeq = createSeqBySchmSeqAndSchSeq.getCreateSeq();
        // originSchCreateDate 추출
        LocalDateTime originSchCreateDate = createSeqBySchmSeqAndSchSeq.getCreateDate();

        // sch 객체에 originSchCreateSeq / originSchCreateDate 값 할당
        sch.setCreateSeq(originSchCreateSeq);
        sch.setCreateDate(originSchCreateDate);

        // sch 객체에 empSeq 값 할당
        sch.setModifySeq(emp.getEmpSeq());

        // 반복문을 통해 participant 객체에 schmSeq , schSeq , createSeq , createDate , modifySeq값 할당
        if (participants != null && participants.size() > 0) {
            for (SchRqDto002.Participant participant : participants) {
                participant.setSchmSeq(curSeq);
                participant.setSchSeq(curSeq);
                participant.setCalSeq(findCalSeq(calendar.getCalSeq(), participant.getCdeSeq()));
                participant.setCreateSeq(originSchCreateSeq);
                participant.setCreateDate(originSchCreateDate);
                participant.setModifySeq(emp.getEmpSeq());
            }
        }

        // 반복문을 통해 disclosureScope 객체에 schmSeq , schSeq , createSeq , createDate , modifySeq값 할당
        if (disclosureScopes != null && disclosureScopes.size() > 0) {
            for (SchRqDto002.DisclosureScope disclosureScope : disclosureScopes) {
                disclosureScope.setSchmSeq(curSeq);
                disclosureScope.setSchSeq(curSeq);
                disclosureScope.setCalSeq(findCalSeq(calendar.getCalSeq(), disclosureScope.getCdeSeq()));
                disclosureScope.setCreateSeq(originSchCreateSeq);
                disclosureScope.setCreateDate(originSchCreateDate);
                disclosureScope.setModifySeq(emp.getEmpSeq());
            }
        }

        log.info("assignObject Method Result Data : " + curSeq);
        log.info("assignObject Method End !!!");
        // return
        return curSeq;
    }

    // 단일 일정 수정 메소드
    @Override
    public int updateSingleSch(SchRqDto002 schRqDto002) throws Exception {
        log.info("updateSingleSch Method Start !!!");
        log.info("updateSingleSch Method Request Data : " + schRqDto002);

        int updateSchCnt = schMapper002.updateSingleSch(schRqDto002);

        log.info("updateSingleSch Method Return Data : " + updateSchCnt);
        log.info("updateSingleSch Method End !!!");
        return updateSchCnt;
    }

    // 일정 참여자 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 10 (참여자))
    @Override
    public int insertSchParticipants(SchRqDto002 schRqDto002) throws Exception {
        log.info("insertSchParticipants Method Start !!!");
        log.info("insertSchParticipants Method Request Data : " + schRqDto002);

        // 참여자 변수 선언
        List<SchRqDto002.Participant> participants = schRqDto002.getParticipants();
        if (participants == null || participants.size() == 0) {
            log.info("insertSchParticipants Method participants == null or participants.size() == 0");
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertRow = schMapper002.insertSchParticipants(participants);

        log.info("insertSchParticipants Method Return Data : " + insertRow);
        log.info("insertSchParticipants Method End !!!");
        // return
        return insertRow;
    }

    // 일정 공개범위 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 20 (참여자))
    @Override
    public int insertSchDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        log.info("insertSchDisclosureScopes Method Start !!!");
        log.info("insertSchDisclosureScopes Method Request Data : " + schRqDto002);

        // 공개범위 변수 선언
        List<SchRqDto002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();
        if (disclosureScopes == null || disclosureScopes.size() == 0) {
            log.info("insertSchDisclosureScopes Method disclosureScopes == null or disclosureScopes.size() == 0");
            return 0;
        }
        // 단일 일정에 포함된 공개범위 등록
        int insertRow = schMapper002.insertSchDisclosureScopes(disclosureScopes);

        log.info("insertSchDisclosureScopes Method Return Data : " + insertRow);
        log.info("insertSchDisclosureScopes Method End !!!");
        // return
        return insertRow;
    }

    @Override
    public int findCalSeq(int calSeq, int cdeSeq) throws Exception {
        log.info("findCalSeq Method Start !!!");
        log.info("findCalSeq Method Request Data : " + "calSeq : " + calSeq + "," + "cdeSeq :" + cdeSeq);
        // return 변수 선언
        int verifyCalSeq = 0;
        int checkCnt = schMapper002.checkCalUser(calSeq, cdeSeq);
        if (checkCnt > 0) {
            log.info("findCalSeq Method checkCnt > 0 !!!");
            verifyCalSeq = calSeq;
        } else {
            log.info("findCalSeq Method checkCnt = 0 !!!");
            int notVerifyCalSeq = schMapper002.checkEcalExist(cdeSeq);
            if (notVerifyCalSeq == 0) {
                log.info("findCalSeq Method notVerifyCalSeq = 0 !!!");
                int newEcalSeq = insertEcalendar(cdeSeq);
                verifyCalSeq = newEcalSeq;
            } else {
                log.info("findCalSeq Method notVerifyCalSeq > 0 !!!");
                verifyCalSeq = notVerifyCalSeq;
            }
        }
        log.info("findCalSeq Method Return Data : " + verifyCalSeq);
        log.info("findCalSeq Method End !!!");
        // return
        return verifyCalSeq;
    }

    @Override
    public int insertEcalendar(int cdeSeq) throws Exception {
        log.info("insertEcalendar Method Start !!!");
        log.info("insertEcalendar Method Request Data : " + cdeSeq);

        int currentCalValue = findCurrentCalValue();
        schMapper002.insertEcal(currentCalValue, cdeSeq);
        schMapper002.insertEcalUser(currentCalValue, cdeSeq);

        log.info("insertEcalendar Method Return Data : " + currentCalValue);
        log.info("insertEcalendar Method End !!!");
        // return
        return currentCalValue;
    }

    @Override
    public int findCurrentCalValue() throws Exception {
        log.info("findCurrentCalValue Method Start !!!");

        int currentCalValue = schMapper002.findCurrentCalValue();

        log.info("findCurrentCalValue Method Return Data : " + currentCalValue);
        log.info("findCurrentCalValue Method End !!!");
        // return
        return currentCalValue;
    }
}
