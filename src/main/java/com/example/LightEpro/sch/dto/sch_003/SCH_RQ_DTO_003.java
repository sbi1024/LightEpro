package com.example.LightEpro.sch.dto.sch_003;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SCH_RQ_DTO_003 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Sch sch;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Sch {
        @Positive
        private int schmSeq;
        @Positive
        private int schSeq;
    }
}
