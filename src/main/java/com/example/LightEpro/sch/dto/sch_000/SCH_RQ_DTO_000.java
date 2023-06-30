package com.example.LightEpro.sch.dto.sch_000;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SCH_RQ_DTO_000 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Sch sch;
    @NotNull
    private @Valid List<Participant> participants;
    @NotNull
    private @Valid List<DisclosureScope> disclosureScopes;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Sch {
        @PositiveOrZero
        private int schmSeq;
        @PositiveOrZero
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
        private int createSeq;
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
        private String useYnd;
        private LocalDateTime createDate;
        private int createSeq;
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
        private String useYnd;
        private LocalDateTime createDate;
        private int createSeq;
    }
}
