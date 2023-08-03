package com.example.LightEpro.sch.dto.sch004;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SchRsDto004 {
    private List<SchInfo> schInfos;
    @Data
    public static class SchInfo {
        private int schmSeq;
        private int schSeq;
        private String schTitle;
        private String schContent;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String alldayYn;
        private int createSeq;
        private LocalDateTime createDate;
        private int modifySeq;
        private LocalDateTime modifyDate;

        private int cdeSeq;
        private String cdeType;
        private int calSeq;
        private int schPartitionType;
        private String schAutority;

        private String calTitle;
        private String calType;
    }
}
