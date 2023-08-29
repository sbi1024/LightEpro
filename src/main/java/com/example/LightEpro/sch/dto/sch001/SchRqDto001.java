package com.example.LightEpro.sch.dto.sch001;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SchRqDto001 {
    @NotNull
    private @Valid User user;
    @NotNull
    private @Valid Schedule schedule;

    @Data
    public static class User {
        @Positive
        private int userCompSeq;
        @Positive
        private int userDeptSeq;
        @Positive
        private int userSeq;
    }

    @Data
    public static class Schedule {
        @Positive
        private int schmSeq;
        @Positive
        private int schSeq;
    }
}
