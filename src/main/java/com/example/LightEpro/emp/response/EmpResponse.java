package com.example.LightEpro.emp.response;

import lombok.Data;

@Data
public class EmpResponse {
    private String responseStatus;
    private int responseCode;
    private String responseMsg;
    private Object responseData;
}
