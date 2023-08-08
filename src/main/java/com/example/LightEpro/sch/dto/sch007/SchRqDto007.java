package com.example.LightEpro.sch.dto.sch007;

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
    private @Valid Calendar calendar;
    @NotNull
    private @Valid Owner owner;
    private @Valid List<Manager> managers;


    private List<CalendarUser> calUsers;
    private List<SchRqDto007.CalendarUser> originMatchCalendarUsers;
    private List<SchRqDto007.CalendarUser> originNonMatchCalendarUsers;
    private List<SchRqDto007.Manager> newNonMatchCalendarUsers;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Calendar {
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

    @Data
    public static class CalendarUser {
        private String calSeq;
        private String cdeSeq;
        private String cdeType;
        private String calPartitionType;
        private String calAutority;
        private String useYnd;
        private String createDate;
        private String createSeq;
        private String modifyDate;
        private String modifySeq;
    }
}
