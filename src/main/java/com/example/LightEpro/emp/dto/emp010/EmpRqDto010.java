package com.example.LightEpro.emp.dto.emp010;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


@Data
public class EmpRqDto010 {
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid EmpAccount empAccount;
    @NotNull
    private @Valid EmpMapping empMapping;

    @Data
    public static class Emp {
        private int empSeq;
        @NotBlank
        private String empName;
        private LocalDateTime birthDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm", timezone = "Asia/Seoul")
        private String phoneNumber;
        private String job;
        private String sex;
    }

    @Data
    public static class EmpAccount {
        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
    }

    @Data
    public static class EmpMapping {
        @Positive
        private int compSeq;
        @Positive
        private int deptSeq;
        @NotBlank
        private String mainCompYn;
        @NotBlank
        private String mainDeptYn;
        @Positive
        private int positionSeq;
        @Positive
        private int positionSpotSeq;
        @Positive
        private int positionGradeSeq;
    }
}
