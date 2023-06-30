package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SCH_MAPPER_001 {
    SCH_RS_DTO_001.Sch findSchBySchmSeqAndSchSeq(SCH_RQ_DTO_001 schRqDto001);
    List<SCH_RS_DTO_001.Participant> findParticipantsBySchmSeqAndSchSeq(SCH_RQ_DTO_001 schRqDto001);
    List<SCH_RS_DTO_001.DisclosureScope> findDisclosureScopesBySchmSeqAndSchSeq(SCH_RQ_DTO_001 schRqDto001);
}
