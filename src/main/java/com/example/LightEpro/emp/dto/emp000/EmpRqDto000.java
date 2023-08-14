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
    private @Valid Dept dept;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Dept {
        private int deptSeq;
        private int parentDeptSeq;
        private String deptPath;
        @NotBlank
        private String deptNameKr;
        private String deptNameEn;
        private String deptNameCn;
        private String deptNameJp;
    }
}
