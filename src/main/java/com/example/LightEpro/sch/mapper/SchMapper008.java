package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchMapper008 {

    String selectCalendarType(SchRqDto008 schRqDto008) throws Exception;

    int updateCalendar(SchRqDto008 schRqDto008) throws Exception;

    int updateCalendarUsers(SchRqDto008 schRqDto008) throws Exception;

    int updateSchedule(SchRqDto008 schRqDto008) throws Exception;

    int updateScheduleUsers(SchRqDto008 schRqDto008) throws Exception;
}
