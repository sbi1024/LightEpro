package com.example.LightEpro.sch.dto.sch001;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SchRsDto001 {
    private Sch sch;
    private List<Participant> participants;
    private List<DisclosureScope> disclosureScopes;

    @Data
    public static class Sch {
        private String schmSeq;
        private String schSeq;
        private String schTitle;
        private String schContent;
        private String startDate;
        private String endDate;
        private String alldayYn;
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
        private String schAutority;
    }

    @Data
    public static class DisclosureScope {
        private String cdeSeq;
        private String cdeType;
        private String schPartitionType;
        private String schAutority;
    }
}
