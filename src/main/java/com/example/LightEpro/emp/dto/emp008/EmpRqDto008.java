package com.example.LightEpro.emp.dto.emp008;

import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto008 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Comp comp;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Comp {
        @Positive
        private int compSeq;
    }
}
