package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch006.SchRqDto006;
import com.example.LightEpro.sch.dto.sch006.SchRsDto006;

import java.util.List;

public interface SchService006 {
    SchRsDto006 findSingleCal(SchRqDto006 schRqDto006) throws Exception;
    SchRsDto006.Calendar findCalendar(SchRqDto006 schRqDto006) throws Exception;
    List<SchRsDto006.CalendarUser> findCalendarUsers(SchRqDto006 schRqDto006) throws Exception;
}
