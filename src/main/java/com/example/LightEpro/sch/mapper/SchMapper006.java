package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch006.SchRqDto006;
import com.example.LightEpro.sch.dto.sch006.SchRsDto006;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper006 {
    SchRsDto006.Calendar selectCalendar(SchRqDto006 schRqDto006) throws Exception;
    List<SchRsDto006.CalendarUser> selectCalendarUsers(SchRqDto006 schRqDto006) throws Exception;
    int checkCalExist(SchRqDto006 schRqDto006) throws Exception;
}
