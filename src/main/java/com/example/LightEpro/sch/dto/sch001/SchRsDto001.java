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
        private int schmSeq;
        private int schSeq;
        private String schTitle;
        private String schContent;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String alldayYn;
        private LocalDateTime createDate;
        private int createSeq;
        private LocalDateTime modifyDate;
        private int modifySeq;
    }

    @Data
    public static class Participant {
        private int cdeSeq;
        private String cdeType;
        private int schPartitionType;
        private String schAutority;
    }

    @Data
    public static class DisclosureScope {
        private int cdeSeq;
        private String cdeType;
        private int schPartitionType;
        private String schAutority;
    }
}
