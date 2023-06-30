package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;

public interface SCH_SERVICE_002 {
    void updateSingleSch(SCH_RQ_DTO_002 schRqDto002)throws Exception;
    int deleteSchUsers(SCH_RQ_DTO_002 schRqDto002) throws Exception;
    int modifySingleSch(SCH_RQ_DTO_002 schRqDto002) throws Exception;

    int insertSchParticipants(SCH_RQ_DTO_002 schRqDto) throws Exception;
    int insertSchDisclosureScopes(SCH_RQ_DTO_002 schRqDto) throws Exception;


}
