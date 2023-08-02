package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch001.SchRsDto001;

public interface SchService001 {
    SchRsDto001 findSingleSch(SchRqDto001 schRqDto001) throws Exception;
}
