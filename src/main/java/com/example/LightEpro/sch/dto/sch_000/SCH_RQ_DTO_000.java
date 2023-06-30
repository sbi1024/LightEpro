package com.example.LightEpro.sch.dto.sch_000;

import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SCH_RQ_DTO_000 {
    @NotNull
    private Sch sch;
    @NotNull
    private List<@Valid Participant> participants;
    @NotNull
    private List<@Valid DisclosureScope> disclosureScopes;

    @Data
    public static class Sch {
        @NotNull
        private int schmSeq;
        @NotNull
        private int schSeq;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime startDate;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime endDate;
        private String alldayYn;
        @NotNull
        private String schTitle;
        @NotNull
        private String schContent;
        @NotNull
        private int empSeq;
    }

    @Data
    public static class Participant {
        private int schmSeq;
        private int schSeq;
        @NotNull
        private int cdeSeq;
        @NotNull
        private String cdeType;
        private String schPartitionType;
        private String schAutority;
        private String useYnd;
        private LocalDateTime createDate;
        private int createSeq;
    }

    @Data
    public static class DisclosureScope {
        private int schmSeq;
        private int schSeq;
        @NotNull
        private String cdeSeq;
        @NotNull
        private String cdeType;
        private String schPartitionType;
        private String schAutority;
        private String useYnd;
        private LocalDateTime createDate;
        private int createSeq;
    }
}
