package com.example.LightEpro.sch.dto.sch006;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SchRqDto006 {
    @NotNull
    private @Valid SchRqDto001.Emp emp;
    @NotNull
    private @Valid SchRqDto002.Calendar calendar;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Calendar {
        @Positive
        private int calSeq;
    }
}
