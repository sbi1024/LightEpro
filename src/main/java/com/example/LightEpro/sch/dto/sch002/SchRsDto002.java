package com.example.LightEpro.sch.dto.sch002;

import lombok.*;

@Data
@Builder
public class SchRsDto002 {
    private int schmSeq;
    private int schSeq;
    private int deleteSchCnt;
    private int updateSchCnt;
    private int participantsInsertCnt;
    private int disclosureScopesInsertCnt;
}