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

        private String cdeSeq;
        private String cdeType;
        private String calSeq;
        private String schPartitionType;
        private String schAutority;

        private String calTitle;
        private String calType;
    }
}
