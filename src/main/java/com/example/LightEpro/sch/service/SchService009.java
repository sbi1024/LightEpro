package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch009.SchRqDto009;
import com.example.LightEpro.sch.dto.sch009.SchRsDto009;

import java.util.List;

public interface SchService009 {

    SchRsDto009 findMyCalendarListInfo(SchRqDto009 schRqDto009) throws Exception;

    List<SchRsDto009.Calendar> findMyCalendarList(SchRqDto009 schRqDto009) throws Exception;

    int findMyCalendarListCnt(SchRqDto009 schRqDto009) throws Exception;
}
