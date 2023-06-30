package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;

public interface SCH_SERVICE_001 {
    SCH_RS_DTO_001 findSingleSch(SCH_RQ_DTO_001 schRqDto001);
}
