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
    private @Valid User user;
    @NotNull
    private @Valid Comp comp;

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
        private int compSeq;
        private int parentCompSeq;
        @NotBlank
        private String compName;
    }
}
