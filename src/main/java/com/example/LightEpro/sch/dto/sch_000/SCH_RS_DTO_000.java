package com.example.LightEpro.sch.dto.sch_000;

import lombok.*;

@Data
@Builder
public class SCH_RS_DTO_000 {
    private int schmSeq;
    private int schSeq;
    private int singleSchInsertCnt;
    private int participantsInsertCnt;
    private int disclosureScopesInsertCnt;
}
