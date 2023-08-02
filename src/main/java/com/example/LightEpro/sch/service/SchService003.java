package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import com.example.LightEpro.sch.dto.sch003.SchRsDto003;

public interface SchService003 {
    SchRsDto003 deleteSingleSch(SchRqDto003 schRqDto003) throws Exception;
}
