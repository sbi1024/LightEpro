package com.example.LightEpro.Interceptor;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItMapper {
    int insertLogInfo(ItDto itDto) throws Exception;
}
