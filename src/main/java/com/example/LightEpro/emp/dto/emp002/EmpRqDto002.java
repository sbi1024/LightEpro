package com.example.LightEpro.emp.dto.emp002;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto002 {
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
        @Positive
        private int deptSeq;
        private int parentDeptSeq;
        @NotBlank
        private String deptName;
    }
}
