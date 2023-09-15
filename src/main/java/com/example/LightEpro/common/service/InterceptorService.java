package com.example.LightEpro.common.service;

import com.example.LightEpro.common.dto.InterceptorDto;

public interface InterceptorService {
    void createLogInfo(InterceptorDto interceptorDto) throws Exception;

    void assignObject(InterceptorDto interceptorDto) throws Exception;

    void createLog(InterceptorDto interceptorDto) throws Exception;
}
