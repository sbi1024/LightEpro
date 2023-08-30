package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch002.SchRsDto002;

import java.util.List;

public interface SchService002 {
    SchRsDto002 modifyScheduleInfo(SchRqDto002 schRqDto002) throws Exception;

    void assignObject(SchRqDto002 schRqDto002) throws Exception;

    void confirmScheduleUsers(SchRqDto002 schRqDto002) throws Exception;


    List<SchRqDto002.ScheduleUser> confirmOriginMatchParticipants(List<SchRqDto002.ScheduleUser> originParticipants,
                                                                  List<SchRqDto002.Participant> requestParticipants) throws Exception;

    List<SchRqDto002.ScheduleUser> confirmOriginNonMatchParticipants(List<SchRqDto002.ScheduleUser> originParticipants,
                                                                     List<SchRqDto002.Participant> requestParticipants) throws Exception;

    List<SchRqDto002.Participant> confirmRequestNonMatchParticipants(List<SchRqDto002.ScheduleUser> originParticipants,
                                                                     List<SchRqDto002.Participant> requestParticipants) throws Exception;

    List<SchRqDto002.ScheduleUser> confirmOriginMatchDisclosureScopes(List<SchRqDto002.ScheduleUser> originDisclosureScopes,
                                                                      List<SchRqDto002.DisclosureScope> requestDisclosureScopes) throws Exception;

    List<SchRqDto002.ScheduleUser> confirmOriginNonMatchDisclosureScopes(List<SchRqDto002.ScheduleUser> originDisclosureScopes,
                                                                         List<SchRqDto002.DisclosureScope> requestDisclosureScopes) throws Exception;

    List<SchRqDto002.DisclosureScope> confirmRequestNonMatchDisclosureScopes(List<SchRqDto002.ScheduleUser> originDisclosureScopes,
                                                                             List<SchRqDto002.DisclosureScope> requestDisclosureScopes) throws Exception;


    int modifySchedule(SchRqDto002 schRqDto002) throws Exception;

    int createParticipants(SchRqDto002 schRqDto002) throws Exception;

    int modifyParticipants(SchRqDto002 schRqDto002) throws Exception;

    int removeParticipants(SchRqDto002 schRqDto002) throws Exception;

    int createDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

    int modifyDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

    int removeDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;
}
