package com.example.LightEpro.sch.dto.sch004;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SchRqDto004 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Sch sch;
    @NotNull
    private @Valid Calendar cal;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Sch {
        @Positive
        private int year;
        @Positive
        private int month;
    }
    @Data
    public static class Calendar {
        @NotNull
        private List<Integer> calSeqs;
    }
}
