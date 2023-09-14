package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import com.example.LightEpro.sch.dto.sch008.SchRsDto008;

public interface SchService008 {
    SchRsDto008 removeCalendarInfo(SchRqDto008 schRqDto008) throws Exception;

    int removeCalendar(SchRqDto008 schRqDto008) throws Exception;

    int removeCalendarUsers(SchRqDto008 schRqDto008) throws Exception;

    int removeSchedules(SchRqDto008 schRqDto008) throws Exception;
    int removeScheduleUsers(SchRqDto008 schRqDto008) throws Exception;
}
