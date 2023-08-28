package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch000.SchRsDto000;

public interface SchService000 {
    SchRsDto000 createSingleSch(SchRqDto000 schRqDto) throws Exception;

    void assignObject(SchRqDto000 schRqDto000) throws Exception;

    int findCurrentSchValue() throws Exception;

    int createSchedule(SchRqDto000 schRqDto) throws Exception;

    int createSchParticipants(SchRqDto000 schRqDto) throws Exception;

    int createSchDisclosureScopes(SchRqDto000 schRqDto) throws Exception;
}
