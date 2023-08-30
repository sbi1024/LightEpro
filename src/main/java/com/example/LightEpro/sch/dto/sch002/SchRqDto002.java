package com.example.LightEpro.sch.dto.sch002;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SchRqDto002 {
    @NotNull
    private @Valid User user; // 필수값
    @NotNull
    private @Valid Schedule schedule; // 필수값
    @NotNull
    private @Valid Calendar calendar; // 필수값
    @NotNull
    private @Valid List<Participant> participants; // 필수값
    private @Valid List<DisclosureScope> disclosureScopes; // 필수값이 아님

    private List<ScheduleUser> scheduleUsers; // 필수값이 아님

    List<ScheduleUser> originMatchParticipants; //필수값이 아님
    List<ScheduleUser> originNonMatchParticipants; //필수값이 아님
    List<Participant> requestNonMatchParticipants; //필수값이 아님

    List<ScheduleUser> originMatchDisclosureScopes; //필수값이 아님
    List<ScheduleUser> originNonMatchDisclosureScopes; //필수값이 아님
    List<DisclosureScope> requestNonMatchDisclosureScopes; //필수값이 아님

    @Data
    public static class User {
        @Positive
        private int userCompSeq; // 필수값
        @Positive
        private int userDeptSeq; // 필수값
        @Positive
        private int userSeq; // 필수값
    }

    @Data
    public static class Schedule {
        @Positive
        private int schmSeq; // 필수값
        @Positive
        private int schSeq; // 필수값

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime startDate; // 필수값
        private int startDateYear; // 필수값이 아님
        private int startDateMonth; // 필수값이 아님

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private LocalDateTime endDate; // 필수값
        private int endDateYear; // 필수값이 아님
        private int endDateMonth; // 필수값이 아님


        @NotBlank
        private String alldayYn; // 필수값
        @NotBlank
        private String schTitle; // 필수값
        private String schContent; // 필수값이 아님
    }

    @Data
    public static class Calendar {
        @Positive
        private int calSeq; // 필수값
    }

    @Data
    public static class Participant {
        @Positive
        private int cdeSeq; // 필수값
        @NotBlank
        private String cdeType; // 필수값
    }

    @Data
    public static class DisclosureScope {
        @Positive
        private int cdeSeq; // 필수값
        @NotBlank
        private String cdeType; // 필수값
    }

    @Data
    public static class ScheduleUser {
        private int cdeSeq; // 필수값이 아님
        private String cdeType; // 필수값이 아님
        private String partitionType; // 필수값이 아님
        private String autority; // 필수값이 아님
    }
}
