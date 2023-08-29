package com.example.LightEpro.sch.dto.sch001;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SchRqDto001 {
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
