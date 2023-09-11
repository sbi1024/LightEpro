package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;

import java.util.List;

public interface SchService004 {
    SchRsDto004 findScheduleListInfo(SchRqDto004 schRqDto004) throws Exception;
    List<SchRsDto004.Schedule> findScheduleList(SchRqDto004 schRqDto004) throws Exception;
    int findScheduleListCnt(SchRqDto004 schRqDto004) throws Exception;
}
