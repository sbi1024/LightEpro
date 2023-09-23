package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch009.SchRqDto009;
import com.example.LightEpro.sch.dto.sch009.SchRsDto009;

import java.util.List;

public interface SchService009 {

    SchRsDto009 findMyCalendarsInfo(SchRqDto009 schRqDto009) throws Exception;

    List<SchRsDto009.Calendar> findMyCalendars(SchRqDto009 schRqDto009) throws Exception;

    List<SchRsDto009.Calendar> confirmAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception;

    List<SchRsDto009.Calendar> findAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception;

    List<SchRsDto009.Calendar> confirmUnAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception;

    List<SchRsDto009.Calendar> findUnAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception;
}
