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
    private @Valid User user; // 필수값
    @NotNull
    private @Valid Calendar calendar; // 필수값

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
    public static class Calendar {
        @Positive
        private int calSeq; // 필수값
    }
}
