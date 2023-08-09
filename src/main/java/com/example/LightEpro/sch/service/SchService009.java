package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch009.SchRqDto009;
import com.example.LightEpro.sch.dto.sch009.SchRsDto009;

public interface SchService009 {

    SchRsDto009 findMyCalList(SchRqDto009 schRqDto009) throws Exception;
}
