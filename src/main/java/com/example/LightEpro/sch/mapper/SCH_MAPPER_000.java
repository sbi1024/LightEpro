package com.example.LightEpro.sch.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SCH_MAPPER_000 {
    int findCurrentSchValue(Map<String,Object> request);
    void insertSingleSch(Map<String,Object> request);
}
