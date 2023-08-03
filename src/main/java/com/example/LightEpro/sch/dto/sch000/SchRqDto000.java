package com.example.LightEpro.sch.dto.sch000;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SchRqDto000 {
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
        @Min(0) @Max(0)
        private int schmSeq;
        @Min(0) @Max(0)
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
        @Positive
        private int schPartitionType;
        @NotBlank
        private String schAutority;
        private int calSeq;
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
        @Positive
        private int schPartitionType;
        @NotBlank
        private String schAutority;
        private int calSeq;
        private int createSeq;
    }
}
