package com.example.LightEpro.sch.dto.sch006;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SchRsDto006 {
    private Calendar cal;
    private List<CalendarUser> calUsers;

    @Data
    public static class Calendar {
        private String calSeq;
        private String calTitle;
        private String calContent;
        private String calColor;
        private String calType;
        private String useYnd;
        private String createDate;
        private String createSeq;
        private String modifyDate;
        private String modifySeq;
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
