package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch000.SchRsDto000;

public interface SchService000 {
    SchRsDto000 createScheduleInfo(SchRqDto000 schRqDto000) throws Exception;

    void assignObject(SchRqDto000 schRqDto000) throws Exception;

    void confirmScheduleSequence(SchRqDto000 schRqDto000) throws Exception;

    int findScheduleSequence() throws Exception;

    void confirmScheduleDate(SchRqDto000 schRqDto000) throws Exception;

    int createSchedule(SchRqDto000 schRqDto000) throws Exception;

    int createParticipants(SchRqDto000 schRqDto000) throws Exception;

    int createDisclosureScopes(SchRqDto000 schRqDto000) throws Exception;
}
