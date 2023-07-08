package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch_003.SCH_RQ_DTO_003;
import com.example.LightEpro.sch.dto.sch_003.SCH_RS_DTO_003;
import com.example.LightEpro.sch.dto.sch_004.SCH_RQ_DTO_004;
import com.example.LightEpro.sch.dto.sch_004.SCH_RS_DTO_004;

public interface SCH_SERVICE_004 {
     SCH_RS_DTO_004 selectSchList(SCH_RQ_DTO_004 schRqDto004);
}
