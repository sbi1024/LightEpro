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
    private @Valid User user; // 필수값
    @NotNull
    private @Valid Comp comp; // 필수값
    @NotNull
    private @Valid Dept dept; // 필수값

    @Data
    public static class User {
        @Positive
        private int userCompSeq; // 필수값
        @Positive
        private int userDeptSeq; // 필수값
        @Positive
        private int userSeq; // 필수값
    }

    @Data
    public static class Comp {
        @Positive
        private int compSeq; // 필수값
    }
    @Data
    public static class Dept {
        private int deptSeq; // 필수값이 아님
        private int parentDeptSeq; // 필수값이 아님
        @NotBlank
        private String deptName; // 필수값
    }
}
