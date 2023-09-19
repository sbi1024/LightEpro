package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch001.SchRsDto001;

import java.util.List;

public interface SchService001 {
    SchRsDto001 findScheduleInfo(SchRqDto001 schRqDto001) throws Exception;

    SchRsDto001.Schedule findSchedule(SchRqDto001 schRqDto001) throws Exception;

    List<SchRsDto001.Participant> findParticipants(SchRqDto001 schRqDto001) throws Exception;

    List<SchRsDto001.DisclosureScope> findDisclosureScopes(SchRqDto001 schRqDto001) throws Exception;

    void assignObject(SchRsDto001 schRsDto001) throws Exception;

    void confirmScheduleUserCalendarSequence(SchRsDto001 schRsDto001) throws Exception;

}
