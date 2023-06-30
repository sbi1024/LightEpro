package com.example.LightEpro.sch.dto;

import lombok.Data;

@Data
public class SCH_RESPONSE {
    private String reponseCode;
    private String responseMsg;
    private Object responseData;
}
