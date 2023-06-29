package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.SCH_RQ_DTO_000;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SCH_MAPPER_000 {
    int findCurrentSchValue(SCH_RQ_DTO_000 schRqDto);
    int insertSingleSch(SCH_RQ_DTO_000 schRqDto);
    int insertSchParticipants(List<SCH_RQ_DTO_000.Participant> participants);
    int insertSchDisclosureScopes(List<SCH_RQ_DTO_000.DisclosureScope> disclosureScopes);
}
