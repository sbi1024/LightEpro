package com.example.LightEpro.sch.dto.sch009;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class SchRqDto009 {
    @NotNull
    private @Valid User user; // 필수값
    private List<Integer> authorizedCalendars; // 필수값이 아님
    private List<Integer> unAuthorizedCalendars; // 필수값이 아님

    @Data
    public static class User {
        @Positive
        private int userCompSeq; // 필수값
        @Positive
        private int userDeptSeq; // 필수값
        @Positive
        private int userSeq; // 필수값
    }
}
