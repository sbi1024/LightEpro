package com.example.LightEpro.sch.dto.sch003;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchRsDto003 {
    private int schmSeq;
    private int schSeq;
    private int updateSchDelStCnt;
    private int updateSchUserDelStCnt;
}