package com.example.LightEpro.emp.dto.emp010;

import com.example.LightEpro.emp.dto.emp015.EmpRqDto015;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmpRqDto010 {
    @NotNull
    private @Valid User user;
    @NotNull
    private @Valid Comp comp;
    @NotNull
    private @Valid Position position;

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
        private int compSeq;
    }

    @Data
    public static class Position {
        private int positionCodeSeq;
        @Positive
        private int positionCodeType;
        @NotBlank
        private String positionCodeInfo;
    }
}
