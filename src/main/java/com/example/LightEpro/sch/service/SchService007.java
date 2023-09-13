package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch007.SchRsDto007;

import java.util.List;

public interface SchService007 {
    SchRsDto007 modifyCalendarInfo(SchRqDto007 schRqDto007) throws Exception;

    void assignObject(SchRqDto007 schRqDto007) throws Exception;

    void confirmCalendarUsers(SchRqDto007 schRqDto007) throws Exception;

    List<SchRqDto007.Manager> confirmRequestNonMatchManagers(List<SchRqDto007.Manager> calendarUsers,
                                                             List<SchRqDto007.Manager> managers) throws Exception;

    List<SchRqDto007.Manager> confirmOriginMatchManagers(List<SchRqDto007.Manager> calendarUsers,
                                                         List<SchRqDto007.Manager> managers) throws Exception;

    List<SchRqDto007.Manager> confirmOriginNonMatchManagers(List<SchRqDto007.Manager> calendarUsers,
                                                            List<SchRqDto007.Manager> managers) throws Exception;


    int modifyCalendar(SchRqDto007 schRqDto007) throws Exception;

    int modifyOwner(SchRqDto007 schRqDto007) throws Exception;

    int createManagers(SchRqDto007 schRqDto007) throws Exception;

    int modifyManagers(SchRqDto007 schRqDto007) throws Exception;

    int removeManagers(SchRqDto007 schRqDto007) throws Exception;


}
