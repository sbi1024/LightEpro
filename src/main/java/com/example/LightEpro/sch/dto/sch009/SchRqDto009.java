package com.example.LightEpro.sch.dto.sch009;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SchRqDto009 {
    @NotNull
    private @Valid Emp emp;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }
}
