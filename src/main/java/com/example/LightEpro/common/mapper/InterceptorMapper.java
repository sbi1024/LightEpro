package com.example.LightEpro.common.mapper;

import com.example.LightEpro.common.dto.InterceptorDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterceptorMapper {
    int insertLog(InterceptorDto interceptorDto) throws Exception;
}
