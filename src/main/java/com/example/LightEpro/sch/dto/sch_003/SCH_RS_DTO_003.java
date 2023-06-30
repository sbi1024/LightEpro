package com.example.LightEpro.sch.dto.sch_003;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SCH_RS_DTO_003 {
    private int schmSeq;
    private int schSeq;
    private int updateSchDelStCnt;
    private int updateSchUserDelStCnt;
}