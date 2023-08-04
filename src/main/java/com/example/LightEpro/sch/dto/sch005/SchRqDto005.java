package com.example.LightEpro.sch.dto.sch005;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class SchRqDto005 {
    @NotNull
    private @Valid SchRqDto005.Emp emp;
    @NotNull
    private @Valid SchRqDto005.Calender calender;
    @NotNull
    private @Valid SchRqDto005.Owner owner;
    private @Valid List<Manager> managers;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Calender {
        private int calSeq;
        @NotBlank
        private String calTitle;
        @NotBlank
        private String calType;
        private String calContent;
        private String calColor;
        private int createSeq;
    }

    @Data
    public static class Owner {
        private int calSeq;
        @Positive
        private int cdeSeq;
        @NotBlank
        private String cdeType;
        private int createSeq;
    }

    @Data
    public static class Manager {
        private int calSeq;
        private int cdeSeq;
        private String cdeType;
        private int createSeq;
    }

}
