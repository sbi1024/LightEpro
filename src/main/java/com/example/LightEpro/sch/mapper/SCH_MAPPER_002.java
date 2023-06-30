package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SCH_MAPPER_002 {
    SCH_RQ_DTO_002.Sch findCreateSeqBySchmSeqAndSchSeq(SCH_RQ_DTO_002 schRqDto002);
    int deleteSchUsers(SCH_RQ_DTO_002 schRqDto002);
    int updateSingleSch(SCH_RQ_DTO_002 schRqDto002);
    int insertSchParticipants(List<SCH_RQ_DTO_002.Participant> participants);
    int insertSchDisclosureScopes(List<SCH_RQ_DTO_002.DisclosureScope> disclosureScopes);
}
