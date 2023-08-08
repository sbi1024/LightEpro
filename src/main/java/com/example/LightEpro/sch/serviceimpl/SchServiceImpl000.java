package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch000.SchRsDto000;
import com.example.LightEpro.sch.mapper.SchMapper000;
import com.example.LightEpro.sch.service.SchService000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl000 implements SchService000 {

    private final SchMapper000 schMapper000;

    // 단일 일정 등록 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto000 createSingleSch(SchRqDto000 schRqDto000) throws Exception {
        log.info("createSingleSch Method Start !!!");
        log.info("createSingleSch Method Request Data : " + schRqDto000);
        // 일정 등록을 위한 일정 시퀀스 값 추출
        int curSeq = findCurrentSchValue();

        // 객체 데이터 재 주입 메소드 호출
        assignObject(curSeq, schRqDto000);
        // 단일 일정 등록 메소드 호출
        int singleSchInsertCnt = insertSingleSch(schRqDto000);
        // 단일 일정에 포함된 참여자 등록 메소드 호출
        int participantsInsertCnt = insertSchParticipants(schRqDto000);
        // 단일 일정에 포함된 공개범위 등록 메소드 호출
        int disclosureScopesInsertCnt = insertSchDisclosureScopes(schRqDto000);

        // 단일 일정 등록시 , 참여자에 본인이 포함되어 있는지 확인
        int checkRegistrantCnt = schMapper000.checkRegistrant(curSeq);
        // 참여자에 본인이 포함되어 있지 않다면 , 본인을 참여자로 등록 진행
        if (checkRegistrantCnt == 0) {
            log.info("createSingleSch Method checkRegistrantCnt = 0");
            int insertRegistCnt = schMapper000.insertSchParticipant(curSeq,
                    schRqDto000.getCalendar().getCalSeq(),
                    schRqDto000.getEmp().getEmpSeq());
            // cnt + 1 진행
            participantsInsertCnt += insertRegistCnt;
        }

        // SCH_RS_DTO_000 생성
        SchRsDto000 schRsDto000 = SchRsDto000.builder()
                .schmSeq(curSeq)
                .schSeq(curSeq)
                .singleSchInsertCnt(singleSchInsertCnt)
                .participantsInsertCnt(participantsInsertCnt)
                .disclosureScopesInsertCnt(disclosureScopesInsertCnt)
                .build();

        log.info("createSingleSch Method Return Data : " + schRsDto000);
        log.info("createSingleSch Method End !!!");
        // return
        return schRsDto000;
    }

    // 일정 시퀀스 번호 할당 메소드
    @Override
    public int findCurrentSchValue() throws Exception {
        log.info("findCurrentSchValue Method Start !!!");

        int currentSchValue = schMapper000.findCurrentSchValue();

        log.info("findCurrentSchValue Method Return Data : " + currentSchValue);
        log.info("findCurrentSchValue Method End !!!");
        // return
        return currentSchValue;
    }

    // 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(int curSeq, SchRqDto000 schRqDto000) throws Exception {
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + "curSeq : " + curSeq + "," + "schRqDto000 :" + schRqDto000);

        // schRqDto000 객체로 부터 내부 클래스 객체 생성
        SchRqDto000.Emp emp = schRqDto000.getEmp();
        SchRqDto000.Sch sch = schRqDto000.getSch();
        SchRqDto000.Calendar calendar = schRqDto000.getCalendar();
        List<SchRqDto000.Participant> participants = schRqDto000.getParticipants();
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();

        // 일정 객체에 일정 시퀀스/등록자시퀀스/시작일자,종료일자(분리된 값) 값 할당
        sch.setSchmSeq(curSeq);
        sch.setSchSeq(curSeq);
        sch.setCreateSeq(emp.getEmpSeq());

        // Year,Month,Day 값 추출
        LocalDateTime startDate = sch.getStartDate();
        int startYear = startDate.getYear();
        int startMonth = startDate.getMonthValue();

        // sch에 할당 (startDate)
        sch.setStartDateYear(startYear);
        sch.setStartDateMonth(startMonth);

        // Year,Month,Day 값 추출
        LocalDateTime endDate = sch.getEndDate();
        int endYear = endDate.getYear();
        int endMonth = endDate.getMonthValue();

        // sch에 할당 (endDate)
        sch.setEndDateYear(endYear);
        sch.setEndDateMonth(endMonth);

        // 참여자 데이터 할당
        if (participants != null && participants.size() > 0) {
            // 반복문을 통해 participant 객체에 schmSeq , schSeq , createSeq 값 할당
            for (SchRqDto000.Participant participant : participants) {
                participant.setSchmSeq(curSeq);
                participant.setSchSeq(curSeq);
                participant.setCalSeq(findCalSeq(calendar.getCalSeq(), participant.getCdeSeq()));
                participant.setCreateSeq(emp.getEmpSeq());

            }
        }
        // 공개범위 데이터 할당
        if (disclosureScopes != null && disclosureScopes.size() > 0) {
            // 반복문을 통해 disclosureScope 객체에 schmSeq , schSeq , createSeq 값 할당
            for (SchRqDto000.DisclosureScope disclosureScope : disclosureScopes) {
                disclosureScope.setSchmSeq(curSeq);
                disclosureScope.setSchSeq(curSeq);
                disclosureScope.setCalSeq(findCalSeq(calendar.getCalSeq(), disclosureScope.getCdeSeq()));
                disclosureScope.setCreateSeq(emp.getEmpSeq());
            }
        }
        log.info("assignObject Method Result Data : " + schRqDto000);
        log.info("assignObject Method End !!!");
    }

    // 일정의 참여자 및 공개범위 인원의 캘린더 시퀀스 번호 추출
    @Override
    public int findCalSeq(int calSeq, int cdeSeq) throws Exception {
        log.info("findCalSeq Method Start !!!");
        log.info("findCalSeq Method Request Data : " + "calSeq : " + calSeq + "," + "cdeSeq :" + cdeSeq);
        // return 변수 선언
        int verifyCalSeq = 0;
        int checkCnt = schMapper000.checkCalUser(calSeq, cdeSeq);
        if (checkCnt > 0) {
            log.info("findCalSeq Method checkCnt > 0 !!!");
            verifyCalSeq = calSeq;
        } else {
            log.info("findCalSeq Method checkCnt = 0 !!!");
            int notVerifyCalSeq = schMapper000.checkEcalExist(cdeSeq);
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
        return verifyCalSeq;
    }

    // 일정 데이터 insert 메소드 (TABLE NAME : t_sc_sch)
    @Override
    public int insertSingleSch(SchRqDto000 schRqDto000) throws Exception {
        log.info("insertSingleSch Method Start !!!");
        log.info("insertSingleSch Method Request Data : " + schRqDto000);

        // 일정 변수 선언
        SchRqDto000.Sch sch = schRqDto000.getSch();
        // 단일 일정 등록
        int insertRow = schMapper000.insertSingleSch(sch);

        log.info("insertSingleSch Method Return Data : " + insertRow);
        log.info("insertSingleSch Method End !!!");
        // return
        return insertRow;
    }

    // 일정 참여자 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 10 (참여자))
    @Override
    public int insertSchParticipants(SchRqDto000 schRqDto000) throws Exception {
        log.info("insertSchParticipants Method Start !!!");
        log.info("insertSchParticipants Method Request Data : " + schRqDto000);

        // 참여자 변수 선언
        List<SchRqDto000.Participant> participants = schRqDto000.getParticipants();
        if (participants == null || participants.size() == 0) {
            log.info("insertSchParticipants Method participants Data : null or size = 0");
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertRow = schMapper000.insertSchParticipants(participants);

        log.info("insertSchParticipants Method Return Data : " + insertRow);
        log.info("insertSchParticipants Method End !!!");
        // return
        return insertRow;
    }

    // 일정 공개범위 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 20 (공개범위))
    @Override
    public int insertSchDisclosureScopes(SchRqDto000 schRqDto000) throws Exception {
        log.info("insertSchDisclosureScopes Method Start !!!");
        log.info("insertSchDisclosureScopes Method Request Data : " + schRqDto000);

        // 공개범위 변수 선언
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();
        if (disclosureScopes == null || disclosureScopes.size() == 0) {
            log.info("insertSchDisclosureScopes Method disclosureScopes Data : null or size = 0");
            return 0;
        }
        // 단일 일정에 포함된 공개범위 등록
        int insertRow = schMapper000.insertSchDisclosureScopes(disclosureScopes);

        log.info("insertSchDisclosureScopes Method Return Data : " + insertRow);
        log.info("insertSchDisclosureScopes Method End !!!");
        // return
        return insertRow;
    }

    // 캘린더 시퀀스 번호 할당 메소드
    @Override
    public int findCurrentCalValue() throws Exception {
        log.info("findCurrentCalValue Method Start !!!");

        int currentCalValue = schMapper000.findCurrentCalValue();

        log.info("findCurrentCalValue Method Return Data : " + currentCalValue);
        log.info("findCurrentCalValue Method End !!!");
        // return
        return currentCalValue;
    }

    // 개인 캘린더 강제 생성
    @Override
    public int insertEcalendar(int cdeSeq) throws Exception {
        log.info("insertEcalendar Method Start !!!");
        log.info("insertEcalendar Method Request Data : " + cdeSeq);

        int currentCalValue = findCurrentCalValue();
        schMapper000.insertEcal(currentCalValue, cdeSeq);
        schMapper000.insertEcalUser(currentCalValue, cdeSeq);

        log.info("insertEcalendar Method Return Data : " + currentCalValue);
        log.info("insertEcalendar Method End !!!");
        return currentCalValue;
    }
}
