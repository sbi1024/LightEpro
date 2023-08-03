package com.example.LightEpro.sch.dto.sch000;

import lombok.*;

@Data
@Builder
public class SchRsDto000 {
    private int schmSeq;
    private int schSeq;
    private int singleSchInsertCnt;
    private int participantsInsertCnt;
    private int disclosureScopesInsertCnt;
}
