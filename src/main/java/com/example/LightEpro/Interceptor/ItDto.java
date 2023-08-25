package com.example.LightEpro.Interceptor;

import lombok.Builder;

@Builder
public class ItDto {
    private String transactionId;
    private String apiPath;
    private String requestBody;
    private String responseBody;
}
