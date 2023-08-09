package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import com.example.LightEpro.sch.dto.sch008.SchRsDto008;

public interface SchService008 {
    SchRsDto008 removeSingleCal(SchRqDto008 schRqDto008) throws Exception;

    int removeCalDetailInfo(SchRqDto008 schRqDto008) throws Exception;

    int removeCalUsers(SchRqDto008 schRqDto008) throws Exception;
}
