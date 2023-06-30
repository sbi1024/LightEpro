package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.dto.sch_002.SCH_RS_DTO_002;
import com.example.LightEpro.sch.dto.sch_003.SCH_RQ_DTO_003;
import com.example.LightEpro.sch.dto.sch_003.SCH_RS_DTO_003;

public interface SCH_SERVICE_003 {
    SCH_RS_DTO_003 deleteSingleSch(SCH_RQ_DTO_003 schRqDto003);
}
