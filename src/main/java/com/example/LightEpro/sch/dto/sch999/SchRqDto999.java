package com.example.LightEpro.sch.dto.sch999;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchRqDto999 {
    private User user;
    private Schedule schedule;
    private Calendar calendar;
    private String moduleApiType;
    private String moduleApiPersonality;

    @Data
    public static class User {
        private int userCompSeq;
        private int userDeptSeq;
        private int userSeq;
    }

    @Data
    public static class Calendar {
        private int calSeq;
    }

    @Data
    public static class Schedule {
        private int schmSeq;
        private int schSeq;
    }
}
