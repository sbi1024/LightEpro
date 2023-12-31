package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch005.SchRsDto005;

public interface SchService005 {
    SchRsDto005 createSingleCal(SchRqDto005 schRqDto005) throws Exception;
    int findCurrentCalValue() throws Exception;
    void assignObject(int curSeq, SchRqDto005 schRqDto005) throws Exception;

    int insertSingleCalendar(SchRqDto005 schRqDto005) throws Exception;
    int insertCalOwner(SchRqDto005 schRqDto005) throws Exception;
    int insertCalManager(SchRqDto005 schRqDto005) throws Exception;
}
