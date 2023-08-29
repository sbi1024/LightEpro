package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch002.SchRsDto002;

public interface SchService002 {
    SchRsDto002 modifyScheduleInfo(SchRqDto002 schRqDto002) throws Exception;

    void assignObject(SchRqDto002 schRqDto002) throws Exception;

    void confirmCalendarUsers(SchRqDto002 schRqDto002) throws Exception;

    int modifySchedule(SchRqDto002 schRqDto002) throws Exception;

    int createParticipants(SchRqDto002 schRqDto002) throws Exception;

    int modifyParticipants(SchRqDto002 schRqDto002) throws Exception;

    int removeParticipants(SchRqDto002 schRqDto002) throws Exception;

    int createDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

    int modifyDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

    int removeDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;
}
