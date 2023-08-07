package com.example.LightEpro.sch.dto.sch007;

import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SchRqDto007 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Calender calender;
    @NotNull
    private @Valid Owner owner;
    private @Valid List<Manager> managers;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Calender {
        @Positive
        private int calSeq;
        @NotBlank
        private String calTitle;
        @NotBlank
        private String calType;
        private String calContent;
        private String calColor;
        private int createSeq;
        private LocalDateTime createDate;
        private int modifySeq;
    }

    @Data
    public static class Owner {
        private int calSeq;
        @Positive
        private int cdeSeq;
        @NotBlank
        private String cdeType;
        private int createSeq;
        private LocalDateTime createDate;
        private int modifySeq;
    }

    @Data
    public static class Manager {
        private int calSeq;
        private int cdeSeq;
        private String cdeType;
        private int createSeq;
        private LocalDateTime createDate;
        private int modifySeq;
    }
}
