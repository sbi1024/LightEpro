package com.example.LightEpro.emp.dto.emp015;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Data
public class EmpRqDto015 {
    @NotNull
    private @Valid User user;
    @NotNull
    private @Valid Emp emp;
    @NotNull
    private @Valid Dept dept;
    @NotNull
    private @Valid EmpAccount empAccount;
    @NotNull
    private @Valid EmpMapping empMapping;

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
    public static class Emp {
        private int empSeq;
        @NotBlank
        private String empName;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
        private LocalDate birthDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
        private LocalDate hireDate;
        private String phoneNumber;
        private String sex;
    }

    @Data
    public static class Dept {
        @Positive
        private int deptSeq;
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
