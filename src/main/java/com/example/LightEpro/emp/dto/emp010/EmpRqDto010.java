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
        private int userSeq;
    }

    @Data
    public static class Comp {
        @Positive
        private int compSeq;
    }

    @Data
    public static class Position {
        private int positionCodeSeq;
        @NotBlank
        private String positionCodeType;
        @NotBlank
        private String positionCodeInfo;
    }
}
