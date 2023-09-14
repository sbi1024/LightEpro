package com.example.LightEpro.sch.dto.sch004;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SchRsDto004 {
    private List<Schedule> scheduleList;
    private int scheduleListCnt;
    @Data
    public static class Schedule {
        private String schmSeq;
        private String schSeq;
        private String schTitle;
        private String schContent;
        private String startDate;
        private String endDate;
        private String alldayYn;
        private String createSeq;
        private String createDate;
        private String modifySeq;
        private String modifyDate;

        private String calSeq;

        private String calTitle;
        private String calType;
        private String calContent;
        private String calColor;
    }
}
