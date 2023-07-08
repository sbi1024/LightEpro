package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.example.LightEpro.sch.dto.sch_004.SCH_RQ_DTO_004;
import com.example.LightEpro.sch.dto.sch_004.SCH_RS_DTO_004;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SCH_MAPPER_004 {
    List<SCH_RS_DTO_004.SchInfo> selectSchList(SCH_RQ_DTO_004 schRqDto004);
}
