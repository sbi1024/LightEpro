package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_001;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_002;
import com.example.LightEpro.sch.service.SCH_SERVICE_001;
import com.example.LightEpro.sch.service.SCH_SERVICE_002;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_002 implements SCH_SERVICE_002 {
    private final SCH_MAPPER_002 schMapper002;

    @Override
    public void updateSingleSch(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        // 해당 일정에 대한 t_sc_sch_user 테이블 데이터 삭제
        deleteSchUsers(schRqDto002);
        // 단일 일정 수정 메소드 호출
        int updateSchCnt = modifySingleSch(schRqDto002);
        int participantsInsertCnt = insertSchParticipants(schRqDto002);
        int disclosureScopesInsertCnt = insertSchDisclosureScopes(schRqDto002);
    }

    @Override
    public int deleteSchUsers(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        int deleteSchUsersCnt = schMapper002.deleteSchUsers(schRqDto002);
        return deleteSchUsersCnt;
    }

    @Override
    public int modifySingleSch(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        int updateSchCnt = schMapper002.modifySingleSch(schRqDto002);
        return updateSchCnt;
    }

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
