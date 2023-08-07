package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import com.example.LightEpro.sch.dto.sch003.SchRsDto003;

public interface SchService003 {
    SchRsDto003 removeSingleSch(SchRqDto003 schRqDto003) throws Exception;
    int updateSchDelStatus(SchRqDto003 schRqDto003) throws Exception;
    int updateSchUsersDelStatus(SchRqDto003 schRqDto003) throws Exception;
}
