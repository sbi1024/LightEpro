package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper004 {
    int selectUserCount(SchRqDto004 schRqDto004) throws Exception;

    List<Integer> selectAuthorizedCalendarSequences(SchRqDto004 schRqDto004) throws Exception;

    List<Integer> selectUnAuthorizedCalendarSequences(SchRqDto004 schRqDto004) throws Exception;

    List<SchRsDto004.Schedule> selectSchedulesByAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception;

    List<SchRsDto004.Schedule> selectSchedulesByUnAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception;

}
