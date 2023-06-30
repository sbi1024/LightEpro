package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.dto.sch_003.SCH_RQ_DTO_003;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SCH_MAPPER_003 {
    int updateSchDelStatus(SCH_RQ_DTO_003 schRqDto003);
    int updateSchUsersDelStatus(SCH_RQ_DTO_003 schRqDto003);
}
