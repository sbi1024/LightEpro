package com.example.LightEpro.sch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SCH_RQ_DTO_000 {
    @NotNull
    private String schmSeq;
    @NotNull
    private String schSeq;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
    private LocalDateTime endDate;
    private String alldayYn;

    @NotNull
    private String schTitle;
    private String schContent;

    @NotNull
    private String empSeq;

    @NotNull
    private List<@Valid Participant> participants;
    @NotNull
    private List<@Valid DisclosureScope> disclosureScopes;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class Participant {
        private String schmSeq;
        private String schSeq;
        @NotNull
        @NotBlank
        private String cdeSeq;
        @NotNull
        @NotBlank
        private String cdeType;
        private String schPartitionType;
        private String schAutority;
        private String useYnd;
        private String createDate;
        private String createSeq;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class DisclosureScope {
        private String schmSeq;
        private String schSeq;
        @NotNull
        @NotBlank
        private String cdeSeq;
        @NotNull
        @NotBlank
        private String cdeType;
        private String schPartitionType;
        private String schAutority;
        private String useYnd;
        private String createDate;
        private String createSeq;
    }
}
