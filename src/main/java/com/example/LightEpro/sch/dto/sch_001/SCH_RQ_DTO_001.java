package com.example.LightEpro.sch.dto.sch_001;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SCH_RQ_DTO_001 {
    private int schmSeq;
    private int schSeq;
    private String empSeq;
}
