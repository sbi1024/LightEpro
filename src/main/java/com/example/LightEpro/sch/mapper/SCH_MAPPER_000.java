package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SCH_MAPPER_000 {
    int findCurrentSchValue();
    int insertSingleSch(SCH_RQ_DTO_000 schRqDto);
    int insertSchParticipants(List<SCH_RQ_DTO_000.Participant> participants);
    int insertSchDisclosureScopes(List<SCH_RQ_DTO_000.DisclosureScope> disclosureScopes);
}
