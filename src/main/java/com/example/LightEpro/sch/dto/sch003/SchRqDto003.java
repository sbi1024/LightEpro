package com.example.LightEpro.sch.dto.sch003;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SchRqDto003 {
    @NotNull
    private @Valid User user; // 필수값
    @NotNull
    private @Valid Schedule schedule; // 필수값

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
    public static class Schedule {
        @Positive
        private int schmSeq; // 필수값
        @Positive
        private int schSeq; // 필수값
    }
}
