package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch007.SchRsDto007;

import java.util.List;

public interface SchService007 {
    SchRsDto007 modifySingleCal(SchRqDto007 schRqDto007) throws Exception;

    int assignObject(SchRqDto007 schRqDto007) throws Exception;
    void checkCalUsers(SchRqDto007 schRqDto007) throws Exception;
    List<SchRqDto007.CalendarUser> checkOriginMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers , List<SchRqDto007.Manager> managers) throws Exception;
    List<SchRqDto007.CalendarUser> checkOriginNonMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers , List<SchRqDto007.Manager> managers) throws Exception;
    List<SchRqDto007.Manager> checkNewNonMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers , List<SchRqDto007.Manager> managers) throws Exception;

    int updateSingleCal(SchRqDto007 schRqDto007) throws Exception;

    int removeCalManagers(SchRqDto007 schRqDto007) throws Exception;
    int modifyCalManagers(SchRqDto007 schRqDto007) throws Exception;

    int createCalManagers(SchRqDto007 schRqDto007) throws Exception;
}
