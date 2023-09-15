package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SchMapper005 {
    int selectUserCount(SchRqDto005 schRqDto005) throws Exception;

    int selectCalendarSequence() throws Exception;

    int insertCalendar(SchRqDto005 schRqDto005) throws Exception;

    int insertCalendarOwner(SchRqDto005 schRqDto005) throws Exception;

    int insertCalendarManagers(SchRqDto005 schRqDto005) throws Exception;

}
