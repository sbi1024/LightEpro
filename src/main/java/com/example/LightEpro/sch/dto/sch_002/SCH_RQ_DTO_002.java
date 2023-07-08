package com.example.LightEpro.sch.dto.sch_002;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SCH_RQ_DTO_002 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Sch sch;

    private @Valid List<Participant> participants;

    private @Valid List<DisclosureScope> disclosureScopes;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Sch {
        @Positive
        private int schmSeq;
        @Positive
        private int schSeq;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime startDate;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime endDate;
        @NotBlank
        private String alldayYn;
        @NotBlank
        private String schTitle;
        @NotNull
        private String schContent;
        @Positive
        private int calSeq;
        private LocalDateTime createDate;
        private int createSeq;
        private LocalDateTime modifyDate;
        private int modifySeq;
    }

    @Data
    public static class Participant {
        private int schmSeq;
        private int schSeq;
        @Positive
        private int cdeSeq;
        @NotBlank
        private String cdeType;
        @NotBlank
        private String schPartitionType;
        @NotBlank
        private String schAutority;
        @PositiveOrZero
        private int calSeq;
        private String useYnd;
        private LocalDateTime createDate;
        private int createSeq;
        private LocalDateTime modifyDate;
        private int modifySeq;
    }

    @Data
    public static class DisclosureScope {
        private int schmSeq;
        private int schSeq;
        @Positive
        private int cdeSeq;
        @NotBlank
        private String cdeType;
        @NotBlank
        private String schPartitionType;
        @NotBlank
        private String schAutority;
        @PositiveOrZero
        private int calSeq;
        private String useYnd;
        private LocalDateTime createDate;
        private int createSeq;
        private LocalDateTime modifyDate;
        private int modifySeq;
    }
}
