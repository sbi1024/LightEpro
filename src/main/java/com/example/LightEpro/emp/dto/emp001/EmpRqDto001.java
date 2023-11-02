package com.example.LightEpro.emp.dto.emp001;

import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto001 {
    @NotNull
    private @Valid User user; // 필수값
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
    public static class Dept {
        @Positive
        private int deptSeq; // 필수값
    }

}
