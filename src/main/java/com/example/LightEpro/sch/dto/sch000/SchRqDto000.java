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
    @NotNull
    private @Valid Calendar calendar;

    private @Valid List<Participant> participants;

    private @Valid List<DisclosureScope> disclosureScopes;

    @Data
    public static class Emp {
        @Positive
        private int empSeq;
    }

    @Data
    public static class Sch {
        private int schmSeq;
        private int schSeq;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime startDate;
        private int startDateYear;
        private int startDateMonth;
        private int startDateDay;


        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime endDate;
        private int endDateYear;
        private int endDateMonth;
        private int endDateDay;
        @NotBlank
        private String alldayYn;
        @NotBlank
        private String schTitle;
        private String schContent;
        private int createSeq;
    }

    @Data
    public static class Calendar {
        @Positive
        private int calSeq;
    }

    @Data
    public static class Participant {
        private int schmSeq;
        private int schSeq;
        @Positive
        private int cdeSeq;
        @NotBlank
        private String cdeType;
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
        private int calSeq;
        private int createSeq;
    }
}
