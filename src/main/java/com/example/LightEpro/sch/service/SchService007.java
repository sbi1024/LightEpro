package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch007.SchRsDto007;

import java.util.List;

public interface SchService007 {
    SchRsDto007 modifySingleCal(SchRqDto007 schRqDto007) throws Exception;
    int assignObject(SchRqDto007 schRqDto007) throws Exception;
    int deleteCalUsers(SchRqDto007 schRqDto007) throws Exception;
    int updateSingleCal(SchRqDto007 schRqDto007) throws Exception;
    int insertCalOwner(SchRqDto007 schRqDto007) throws Exception;
    int insertCalManager(List<SchRqDto007.Manager> managers) throws Exception;
}
