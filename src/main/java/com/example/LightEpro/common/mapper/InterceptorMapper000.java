package com.example.LightEpro.common.mapper;

import com.example.LightEpro.common.dto.InterceptorDto000;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterceptorMapper000 {
    int insertLog(InterceptorDto000 interceptorDto000) throws Exception;
}
