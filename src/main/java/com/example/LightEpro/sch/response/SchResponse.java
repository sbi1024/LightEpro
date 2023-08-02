package com.example.LightEpro.sch.response;

import lombok.Data;

@Data
public class SchResponse {
    private String responseStatus;
    private int reponseCode;
    private String responseMsg;
    private Object responseData;
}
