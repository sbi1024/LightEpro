package com.example.LightEpro.sch.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SCH_MAPPER_001 {
    List<Map<String,Object>> findSchTitleBySchmSeq();
}
