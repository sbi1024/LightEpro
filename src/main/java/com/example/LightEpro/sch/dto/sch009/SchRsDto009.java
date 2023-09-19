package com.example.LightEpro.sch.dto.sch009;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SchRsDto009 {
    private List<Calendar> myCalendarList;
    private int myCalendarListCnt;

    @Data
    public static class Calendar {
        private int calSeq;
        private String calType;
        private String calTitle;
        private String calContent;
        private String calColor;
    }
}
