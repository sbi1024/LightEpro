package com.example.LightEpro.sch.dto.sch002;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class schRsDto002 {
    private int schmSeq;
    private int schSeq;
    private int deleteSchCnt;
    private int updateSchCnt;
    private int participantsInsertCnt;
    private int disclosureScopesInsertCnt;
}