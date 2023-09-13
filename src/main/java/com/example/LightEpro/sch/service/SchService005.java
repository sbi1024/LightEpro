package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch005.SchRsDto005;

public interface SchService005 {
    SchRsDto005 createCalendarInfo(SchRqDto005 schRqDto005) throws Exception;

    void assignObject(SchRqDto005 schRqDto005) throws Exception;

    void confirmCalendarSequence(SchRqDto005 schRqDto005) throws Exception;

    int findCalendarSequence() throws Exception;

    int createCalendar(SchRqDto005 schRqDto005) throws Exception;

    int createOwner(SchRqDto005 schRqDto005) throws Exception;

    int createManagers(SchRqDto005 schRqDto005) throws Exception;
}
