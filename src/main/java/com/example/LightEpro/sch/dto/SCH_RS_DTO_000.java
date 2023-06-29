package com.example.LightEpro.sch.dto;

import lombok.*;

@Data
public class SCH_RS_DTO_000 {
    private String resultCode;
    private String resultMsg;
    private Object resultData;

    @Data
    @Builder
    public static class ResultData {
        private int singleSchInsertCnt;
        private int participantsInsertCnt;
        private int disclosureScopesInsertCnt;
    }
}
