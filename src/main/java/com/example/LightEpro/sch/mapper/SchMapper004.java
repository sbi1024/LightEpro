package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper004 {
    List<SchRsDto004.Schedule> selectScheduleList(SchRqDto004 schRqDto004) throws Exception;

    int selectScheduleListCnt(SchRqDto004 schRqDto004) throws Exception;
}
