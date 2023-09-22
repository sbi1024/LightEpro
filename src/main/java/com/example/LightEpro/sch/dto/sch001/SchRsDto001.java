package com.example.LightEpro.sch.dto.sch001;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SchRsDto001 {
    private Schedule schedule;
    private List<Participant> participants;
    private List<DisclosureScope> disclosureScopes;

    @Data
    public static class Schedule {
        private String schmSeq;
        private String schSeq;
        private String schTitle;
        private String schContent;
        private String startDate;
        private String endDate;
        private String allDayYn;
        private String createDate;
        private String createSeq;
        private String modifyDate;
        private String modifySeq;
    }

    @Data
    public static class Participant {
        private String cdeSeq;
        private String cdeType;
        private String schPartitionType;
        private String schAuthority;
        private String calSeq;
    }

    @Data
    public static class DisclosureScope {
        private String cdeSeq;
        private String cdeType;
        private String schPartitionType;
        private String schAuthority;
        private String calSeq;
    }
}
