package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;

public interface SchService004 {
     SchRsDto004 selectSchList(SchRqDto004 schRqDto004) throws Exception;
}
