package com.example.LightEpro.emp.dto.emp006;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto006 {
    @NotNull
    private @Valid User user; // 필수값
    @NotNull
    private @Valid Comp comp; // 필수값

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
}
