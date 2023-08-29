package com.example.LightEpro.emp.dto.emp000;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class EmpRqDto000 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Comp comp;
    @NotNull
    private @Valid Dept dept;

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
    @Data
    public static class Dept {
        private int deptSeq;
        private int parentDeptSeq;
        @NotBlank
        private String deptName;
    }
}
