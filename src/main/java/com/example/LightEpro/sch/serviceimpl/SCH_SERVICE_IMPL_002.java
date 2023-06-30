package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.dto.sch_002.SCH_RS_DTO_002;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_001;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_002;
import com.example.LightEpro.sch.service.SCH_SERVICE_001;
import com.example.LightEpro.sch.service.SCH_SERVICE_002;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_002 implements SCH_SERVICE_002 {
    private final SCH_MAPPER_002 schMapper002;

    @Override
    public SCH_RS_DTO_002 modifySingleSch(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        // 해당 일정에 대한 t_sc_sch_user 테이블 데이터 삭제
        int deleteSchUsersCnt = deleteSchUsers(schRqDto002);
        // 일정 시퀀스 번호 주입 메소드 호출
        int curseq = assignObject(schRqDto002);
        // 단일 일정 수정 메소드 호출
        int updateSchCnt = updateSingleSch(schRqDto002);
        // 일정 참여자 등록 메소드 호출
        int participantsInsertCnt = insertSchParticipants(schRqDto002);
        // 일정 공개범위 등록 메소드 호출
        int disclosureScopesInsertCnt = insertSchDisclosureScopes(schRqDto002);

        // SCH_RS_DTO_002  객체 생성
        SCH_RS_DTO_002 schRsDto002 = SCH_RS_DTO_002.builder()
                .schmSeq(curseq)
                .schSeq(curseq)
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
    public int deleteSchUsers(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        int deleteSchUsersCnt = schMapper002.deleteSchUsers(schRqDto002);
        return deleteSchUsersCnt;
    }

    // 일정 시퀀스 번호 주입 메소드
    @Override
    public int assignObject(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        // schRqDto002 객체로 부터 내부 클래스 객체 생성
        SCH_RQ_DTO_002.Emp emp = schRqDto002.getEmp();
        SCH_RQ_DTO_002.Sch sch = schRqDto002.getSch();
        List<SCH_RQ_DTO_002.Participant> participants = schRqDto002.getParticipants();
        List<SCH_RQ_DTO_002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();

        // 일정 시퀀스 값 할당
        int curSeq = sch.getSchmSeq();

        // 본래 일정의 createSeq , createDate 값을 추출
        SCH_RQ_DTO_002.Sch createSeqBySchmSeqAndSchSeq = schMapper002.findCreateSeqBySchmSeqAndSchSeq(schRqDto002);
        // originCreateSeq 추출
        int originSchCreateSeq = createSeqBySchmSeqAndSchSeq.getCreateSeq();
        // originSchCreateDate 추출
        LocalDateTime originSchCreateDate = createSeqBySchmSeqAndSchSeq.getCreateDate();

        // sch 객체에 empSeq 값 할당
        sch.setModifySeq(emp.getEmpSeq());

        // 반복문을 통해 participant 객체에 schmSeq , schSeq , createSeq , createDate , modifySeq값 할당
        for (SCH_RQ_DTO_002.Participant participant : participants) {
            participant.setSchmSeq(curSeq);
            participant.setSchSeq(curSeq);
            participant.setCreateSeq(originSchCreateSeq);
            participant.setCreateDate(originSchCreateDate);
            participant.setModifySeq(emp.getEmpSeq());
        }

        // 반복문을 통해 disclosureScope 객체에 schmSeq , schSeq , createSeq , createDate , modifySeq값 할당
        for (SCH_RQ_DTO_002.DisclosureScope disclosureScope : disclosureScopes) {
            disclosureScope.setSchmSeq(curSeq);
            disclosureScope.setSchSeq(curSeq);
            disclosureScope.setCreateSeq(originSchCreateSeq);
            disclosureScope.setCreateDate(originSchCreateDate);
            disclosureScope.setModifySeq(emp.getEmpSeq());
        }

        // return
        return curSeq;
    }

    // 단일 일정 수정 메소드
    @Override
    public int updateSingleSch(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        int updateSchCnt = schMapper002.updateSingleSch(schRqDto002);
        return updateSchCnt;
    }

    // 일정 참여자 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 10 (참여자))
    @Override
    public int insertSchParticipants(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        // 참여자 변수 선언
        List<SCH_RQ_DTO_002.Participant> participants = schRqDto002.getParticipants();
        if (participants.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertRow = schMapper002.insertSchParticipants(participants);
        // return
        return insertRow;
    }

    // 일정 공개범위 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 20 (참여자))
    @Override
    public int insertSchDisclosureScopes(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        // 공개범위 변수 선언
        List<SCH_RQ_DTO_002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();
        if (disclosureScopes.size() == 0) {
            return 0;
        }
        // 단일 일정에 포함된 공개범위 등록
        int insertRow = schMapper002.insertSchDisclosureScopes(disclosureScopes);
        // return
        return insertRow;
    }
}
