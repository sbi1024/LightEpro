package com.example.LightEpro.emp.dto.emp006;

import com.example.LightEpro.emp.dto.emp005.EmpRqDto005;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto006 {
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
