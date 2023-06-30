package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.dto.sch_002.SCH_RS_DTO_002;

public interface SCH_SERVICE_002 {
    SCH_RS_DTO_002 modifySingleSch(SCH_RQ_DTO_002 schRqDto002)throws Exception;
    int deleteSchUsers(SCH_RQ_DTO_002 schRqDto002) throws Exception;
    int assignObject(SCH_RQ_DTO_002 schRqDto002) throws Exception;
    int updateSingleSch(SCH_RQ_DTO_002 schRqDto002) throws Exception;

    int insertSchParticipants(SCH_RQ_DTO_002 schRqDto) throws Exception;
    int insertSchDisclosureScopes(SCH_RQ_DTO_002 schRqDto) throws Exception;


}
