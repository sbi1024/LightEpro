package com.example.LightEpro.emp.dto.emp003;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto003 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Dept dept;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Dept {
        @Positive
        private int deptSeq;
    }
}
