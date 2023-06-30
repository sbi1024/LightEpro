package com.example.LightEpro.sch.dto.sch_002;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SCH_RS_DTO_002 {
    private int schmSeq;
    private int schSeq;
    private int deleteSchCnt;
    private int updateSchCnt;
    private int participantsInsertCnt;
    private int disclosureScopesInsertCnt;
}