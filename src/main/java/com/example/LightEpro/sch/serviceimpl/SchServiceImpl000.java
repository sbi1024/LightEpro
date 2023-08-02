package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch000.SchRsDto000;
import com.example.LightEpro.sch.mapper.SchMapper000;
import com.example.LightEpro.sch.service.SchService000;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchServiceImpl000 implements SchService000 {

    private final SchMapper000 schMapper000;

    // 단일 일정 등록 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto000 createSingleSch(SchRqDto000 schRqDto000) throws Exception {
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
            int insertRegistCnt = schMapper000.insertSchParticipant(curSeq,
                    schRqDto000.getSch().getCalSeq(),
                    schRqDto000.getEmp().getEmpSeq());
            // cnt + 1 진행
            participantsInsertCnt += insertRegistCnt;
        }

        // SCH_RS_DTO_000 생성
        SchRsDto000 rsDto000 = SchRsDto000.builder()
                .schmSeq(curSeq)
                .schSeq(curSeq)
                .singleSchInsertCnt(singleSchInsertCnt)
                .participantsInsertCnt(participantsInsertCnt)
                .disclosureScopesInsertCnt(disclosureScopesInsertCnt)
                .build();

        // return
        return rsDto000;
    }

    // 일정 시퀀스 번호 할당 메소드
    @Override
    public int findCurrentSchValue() throws Exception {
        int currentSchValue = schMapper000.findCurrentSchValue();
        // return
        return currentSchValue;
    }

    // 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(int curSeq, SchRqDto000 schRqDto000) throws Exception {
        // schRqDto000 객체로 부터 내부 클래스 객체 생성
        SchRqDto000.Emp emp = schRqDto000.getEmp();
        SchRqDto000.Sch sch = schRqDto000.getSch();
        List<SchRqDto000.Participant> participants = schRqDto000.getParticipants();
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();

        // 일정 객체에 curSeq 값 할당
        sch.setSchmSeq(curSeq);
        sch.setSchSeq(curSeq);
        sch.setCreateSeq(emp.getEmpSeq());

        if (participants != null && participants.size() > 0) {
            // 반복문을 통해 participant 객체에 schmSeq , schSeq , createSeq 값 할당
            for (SchRqDto000.Participant participant : participants) {
                participant.setSchmSeq(curSeq);
                participant.setSchSeq(curSeq);
                participant.setCalSeq(findCalSeq(sch.getCalSeq(), participant.getCdeSeq()));
                participant.setCreateSeq(emp.getEmpSeq());

            }
        }

        if (disclosureScopes != null && disclosureScopes.size() > 0) {
            // 반복문을 통해 disclosureScope 객체에 schmSeq , schSeq , createSeq 값 할당
            for (SchRqDto000.DisclosureScope disclosureScope : disclosureScopes) {
                disclosureScope.setSchmSeq(curSeq);
                disclosureScope.setSchSeq(curSeq);
                disclosureScope.setCalSeq(findCalSeq(sch.getCalSeq(), disclosureScope.getCdeSeq()));
                disclosureScope.setCreateSeq(emp.getEmpSeq());
            }
        }
    }

    // 일정의 참여자 및 공개범위 인원의 캘린더 시퀀스 번호 추출
    @Override
    public int findCalSeq(int calSeq, int cdeSeq) throws Exception {
        // return 변수 선언
        int verifyCalSeq = 0;
        int checkCnt = schMapper000.checkCalUser(calSeq, cdeSeq);
        if (checkCnt > 0) {
            verifyCalSeq = calSeq;
        } else {
            int notVerifyCalSeq = schMapper000.checkEcalExist(cdeSeq);
            if (notVerifyCalSeq == 0) {
                int newEcalSeq = insertEcalendar(cdeSeq);
                verifyCalSeq = newEcalSeq;
            } else {
                verifyCalSeq = notVerifyCalSeq;
            }
        }
        return verifyCalSeq;
    }

    // 일정 데이터 insert 메소드 (TABLE NAME : t_sc_sch)
    @Override
    public int insertSingleSch(SchRqDto000 schRqDto000) throws Exception {
        // 일정 변수 선언
        SchRqDto000.Sch sch = schRqDto000.getSch();
        if (sch == null) {
            return 0;
        }
        // 단일 일정 등록
        int insertRow = schMapper000.insertSingleSch(sch);
        // return
        return insertRow;
    }

    // 일정 참여자 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 10 (참여자))
    @Override
    public int insertSchParticipants(SchRqDto000 schRqDto000) throws Exception {
        // 참여자 변수 선언
        List<SchRqDto000.Participant> participants = schRqDto000.getParticipants();
        if (participants == null || participants.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertRow = schMapper000.insertSchParticipants(participants);
        // return
        return insertRow;
    }

    // 일정 공개범위 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 20 (공개범위))
    @Override
    public int insertSchDisclosureScopes(SchRqDto000 schRqDto000) throws Exception {
        // 공개범위 변수 선언
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();
        if (disclosureScopes == null || disclosureScopes.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 공개범위 등록
        int insertRow = schMapper000.insertSchDisclosureScopes(disclosureScopes);
        // return
        return insertRow;
    }

    // 캘린더 시퀀스 번호 할당 메소드
    @Override
    public int findCurrentCalValue() throws Exception {
        int currentCalValue = schMapper000.findCurrentCalValue();
        // return
        return currentCalValue;
    }

    // 개인 캘린더 강제 생성
    @Override
    public int insertEcalendar(int cdeSeq) throws Exception {
        int currentCalValue = findCurrentCalValue();
        schMapper000.insertEcal(currentCalValue, cdeSeq);
        schMapper000.insertEcalUser(currentCalValue, cdeSeq);
        return currentCalValue;
    }
}
