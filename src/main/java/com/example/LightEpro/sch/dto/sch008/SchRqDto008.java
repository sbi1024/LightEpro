package com.example.LightEpro.sch.dto.sch008;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SchRqDto008 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Calendar calendar;

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
