package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchMapper008 {
    int selectUserCount(SchRqDto008 schRqDto008) throws Exception;

    int selectCalendarCount(SchRqDto008 schRqDto008) throws Exception;

    int updateCalendarAndSchedulesAndUsers(SchRqDto008 schRqDto008) throws Exception;
}
