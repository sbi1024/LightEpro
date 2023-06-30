package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_000.SCH_RS_DTO_000;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_000;
import com.example.LightEpro.sch.service.SCH_SERVICE_000;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_000 implements SCH_SERVICE_000 {

    private final SCH_MAPPER_000 schMapper000;

    // 단일 일정 등록 메소드
    @Override
    public SCH_RS_DTO_000 createSingleSch(SCH_RQ_DTO_000 schRqDto000) throws Exception {
        // 일정 등록을 위한 일정 시퀀스 값 추출 (int 형)
        int curSeq = findCurrentSchValue();
        // 일정 시퀀스 번호 주입 메소드 호출
        setSchmSeqAndSchseq(curSeq, schRqDto000);
        // 단일 일정 등록 메소드 호출
        int singleSchInsertCnt = insertSingleSch(schRqDto000);
        // 단일 일정에 포함된 참여자 등록 메소드 호출
        int participantsInsertCnt = insertSchParticipants(schRqDto000);
        // 단일 일정에 포함된 공개범위 등록 메소드 호출
        int disclosureScopesInsertCnt = insertSchDisclosureScopes(schRqDto000);

        // SCH_RS_DTO_000 생성
        SCH_RS_DTO_000 rsDto000 = SCH_RS_DTO_000.builder()
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

    // 일정 시퀀스 번호 주입 메소드
    @Override
    public void setSchmSeqAndSchseq(int curSeq, SCH_RQ_DTO_000 schRqDto000) throws Exception {
        // schRqDto000 객체로 부터 내부 클래스 객체 생성
        SCH_RQ_DTO_000.Sch sch = schRqDto000.getSch();
        List<SCH_RQ_DTO_000.Participant> participants = schRqDto000.getParticipants();
        List<SCH_RQ_DTO_000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();

        // 일정 객체에 curSeq 값 할당
        sch.setSchmSeq(curSeq);
        sch.setSchSeq(curSeq);

        // 반복문을 통해 participant 객체에 schmSeq , schSeq , createSeq 값 할당
        for (SCH_RQ_DTO_000.Participant participant : participants) {
            participant.setSchmSeq(curSeq);
            participant.setSchSeq(curSeq);
            participant.setCreateSeq(sch.getEmpSeq());
        }

        // 반복문을 통해 disclosureScope 객체에 schmSeq , schSeq , createSeq 값 할당
        for (SCH_RQ_DTO_000.DisclosureScope disclosureScope : disclosureScopes) {
            disclosureScope.setSchmSeq(curSeq);
            disclosureScope.setSchSeq(curSeq);
            disclosureScope.setCreateSeq(sch.getEmpSeq());
        }
    }

    // 일정 데이터 insert 메소드 (TABLE NAME : t_sc_sch)
    @Override
    public int insertSingleSch(SCH_RQ_DTO_000 schRqDto000) throws Exception {
        // 단일 일정 등록
        int insertRow = schMapper000.insertSingleSch(schRqDto000);
        // return
        return insertRow;
    }

    // 일정 참여자 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 10 (참여자))
    @Override
    public int insertSchParticipants(SCH_RQ_DTO_000 schRqDto000) throws Exception {
        // 참여자 변수 선언
        List<SCH_RQ_DTO_000.Participant> participants = schRqDto000.getParticipants();
        if (participants.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertRow = schMapper000.insertSchParticipants(participants);
        // return
        return insertRow;
    }

    // 일정 공개범위 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 20 (공개범위))
    @Override
    public int insertSchDisclosureScopes(SCH_RQ_DTO_000 schRqDto000) throws Exception {
        // 공개범위 변수 선언
        List<SCH_RQ_DTO_000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();
        if (disclosureScopes.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 공개범위 등록
        int insertRow = schMapper000.insertSchDisclosureScopes(disclosureScopes);
        // return
        return insertRow;
    }
}
