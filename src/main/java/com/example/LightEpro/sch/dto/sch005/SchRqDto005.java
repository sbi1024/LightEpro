package com.example.LightEpro.sch.dto.sch005;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class SchRqDto005 {
    @NotNull
    private @Valid User user; // 필수값
    @NotNull
    private @Valid Calendar calendar; // 필수값
    @NotNull
    private @Valid Owner owner; // 필수값 
    private @Valid List<Manager> managers; // 필수값이 아님

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
    public static class Calendar {
        private int calSeq;
        @NotBlank
        private String calTitle; // 필수값
        @NotBlank
        private String calType; // 필수값
        private String calContent; // 필수값이 아님
        private String calColor; // 필수값이 아님
    }

    @Data
    public static class Owner {
        @Positive
        private int cdeSeq; // 필수값
        @NotBlank
        private String cdeType; // 필수값
    }

    @Data
    public static class Manager {
        @Positive
        private int cdeSeq; // 필수값
        @NotBlank
        private String cdeType; // 필수값
    }

}
