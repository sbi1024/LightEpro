package com.example.LightEpro.sch.dto.sch_003;

import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SCH_RQ_DTO_003 {
    @NotNull
    private @Valid SCH_RQ_DTO_001.Emp emp;
    @NotNull
    private @Valid SCH_RQ_DTO_001.Sch sch;

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
