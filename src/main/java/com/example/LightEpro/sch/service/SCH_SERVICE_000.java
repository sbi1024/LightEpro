package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.SCH_RS_DTO_000;

import java.util.List;
import java.util.Map;

public interface SCH_SERVICE_000 {
    int findCurrentSchValue(SCH_RQ_DTO_000 schRqDto) throws Exception;
    SCH_RS_DTO_000.ResultData createSingleSch(SCH_RQ_DTO_000 schRqDto) throws Exception;
    int insertSingleSch(SCH_RQ_DTO_000 schRqDto) throws Exception;
    int insertSchParticipants(SCH_RQ_DTO_000 schRqDto) throws Exception;
    int insertSchDisclosureScopes(SCH_RQ_DTO_000 schRqDto) throws Exception;
}
