package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;

import java.util.List;

public interface SchService004 {
    SchRsDto004 findSchedulesInfo(SchRqDto004 schRqDto004) throws Exception;

    List<SchRsDto004.Schedule> findSchedules(SchRqDto004 schRqDto004) throws Exception;

    List<SchRsDto004.Schedule> confirmSchedulesByAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception;

    List<SchRsDto004.Schedule> findSchedulesByAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception;

    List<SchRsDto004.Schedule> confirmSchedulesByUnAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception;

    List<SchRsDto004.Schedule> findSchedulesByUnAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception;

}
