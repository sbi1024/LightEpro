package com.example.LightEpro.common.dto;

import lombok.Builder;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
@Builder
public class InterceptorDto {
    private HttpServletRequest request;
    private String requestBody;
    private String responseBody;
    private String transactionId;
    private String apiPath;
}
