package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchMapper003 {
    int selectScheduleCount(SchRqDto003 schRqDto003) throws Exception;

    int updateSchedule(SchRqDto003 schRqDto003) throws Exception;

    int updateScheduleUsers(SchRqDto003 schRqDto003) throws Exception;
}
