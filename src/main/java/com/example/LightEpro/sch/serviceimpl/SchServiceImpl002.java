package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch002.schRsDto002;
import com.example.LightEpro.sch.mapper.SchMapper002;
import com.example.LightEpro.sch.service.SchService002;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchServiceImpl002 implements SchService002 {
    private final SchMapper002 schMapper002;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public schRsDto002 modifySingleSch(SchRqDto002 schRqDto002) throws Exception {
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

        // 단일 일정 등록시 , 참여자에 본인이 포함되어 있는지 확인
        int checkRegistrantCnt = schMapper002.checkRegistrant(curSeq);
        // 참여자에 본인이 포함되어 있지 않다면 , 본인을 참여자로 등록 진행
        if (checkRegistrantCnt == 0) {
            int insertRegistCnt = schMapper002.insertSchParticipant(curSeq,
                    schRqDto002.getSch().getCalSeq(),
                    schRqDto002.getEmp().getEmpSeq());
            // cnt + 1 진행
            participantsInsertCnt += insertRegistCnt;
        }

        // SCH_RS_DTO_002  객체 생성
        schRsDto002 schRsDto002 = com.example.LightEpro.sch.dto.sch002.schRsDto002.builder()
                .schmSeq(curSeq)
                .schSeq(curSeq)
                .deleteSchCnt(deleteSchUsersCnt)
                .updateSchCnt(updateSchCnt)
                .participantsInsertCnt(participantsInsertCnt)
                .disclosureScopesInsertCnt(disclosureScopesInsertCnt)
                .build();

        // return
        return schRsDto002;
    }

    // 일정 참여자 데이터 전체 삭제
    @Override
    public int deleteSchUsers(SchRqDto002 schRqDto002) throws Exception {
        int deleteSchUsersCnt = schMapper002.deleteSchUsers(schRqDto002);
        return deleteSchUsersCnt;
    }

    // 일정 시퀀스 번호 주입 메소드
    @Override
    public int assignObject(SchRqDto002 schRqDto002) throws Exception {
        // schRqDto002 객체로 부터 내부 클래스 객체 생성
        SchRqDto002.Emp emp = schRqDto002.getEmp();
        SchRqDto002.Sch sch = schRqDto002.getSch();
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
        // sch 객체에 empSeq 값 할당
        sch.setModifySeq(emp.getEmpSeq());

        // 반복문을 통해 participant 객체에 schmSeq , schSeq , createSeq , createDate , modifySeq값 할당
        if (participants != null && participants.size() > 0) {
            for (SchRqDto002.Participant participant : participants) {
                participant.setSchmSeq(curSeq);
                participant.setSchSeq(curSeq);
                participant.setCalSeq(findCalSeq(sch.getCalSeq(), participant.getCdeSeq()));
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
                disclosureScope.setCalSeq(findCalSeq(sch.getCalSeq(), disclosureScope.getCdeSeq()));
                disclosureScope.setCreateSeq(originSchCreateSeq);
                disclosureScope.setCreateDate(originSchCreateDate);
                disclosureScope.setModifySeq(emp.getEmpSeq());
            }
        }

        // return
        return curSeq;
    }

    // 단일 일정 수정 메소드
    @Override
    public int updateSingleSch(SchRqDto002 schRqDto002) throws Exception {
        int updateSchCnt = schMapper002.updateSingleSch(schRqDto002);
        return updateSchCnt;
    }

    // 일정 참여자 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 10 (참여자))
    @Override
    public int insertSchParticipants(SchRqDto002 schRqDto002) throws Exception {
        // 참여자 변수 선언
        List<SchRqDto002.Participant> participants = schRqDto002.getParticipants();
        if (participants == null || participants.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertRow = schMapper002.insertSchParticipants(participants);
        // return
        return insertRow;
    }

    // 일정 공개범위 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 20 (참여자))
    @Override
    public int insertSchDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        // 공개범위 변수 선언
        List<SchRqDto002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();
        if (disclosureScopes == null || disclosureScopes.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 공개범위 등록
        int insertRow = schMapper002.insertSchDisclosureScopes(disclosureScopes);
        // return
        return insertRow;
    }

    @Override
    public int findCalSeq(int calSeq, int cdeSeq) throws Exception {
        // return 변수 선언
        int verifyCalSeq = 0;
        int checkCnt = schMapper002.checkCalUser(calSeq, cdeSeq);
        if (checkCnt > 0) {
            verifyCalSeq = calSeq;
        } else {
            int notVerifyCalSeq = schMapper002.checkEcalExist(cdeSeq);
            if (notVerifyCalSeq == 0) {
                int newEcalSeq = insertEcalendar(cdeSeq);
                verifyCalSeq = newEcalSeq;
            } else {
                verifyCalSeq = notVerifyCalSeq;
            }
        }
        return verifyCalSeq;
    }

    @Override
    public int insertEcalendar(int cdeSeq) throws Exception {
        int currentCalValue = findCurrentCalValue();
        schMapper002.insertEcal(currentCalValue, cdeSeq);
        schMapper002.insertEcalUser(currentCalValue, cdeSeq);
        return currentCalValue;
    }

    @Override
    public int findCurrentCalValue() throws Exception {
        int currentCalValue = schMapper002.findCurrentCalValue();
        // return
        return currentCalValue;
    }
}
