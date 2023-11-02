package com.example.LightEpro.emp.dto.emp005;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto005 {
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
        private int compSeq; // 필수값이 아님
        private int parentCompSeq; // 필수값이 아님
        @NotBlank
        private String compName; // 필수값
    }
}
