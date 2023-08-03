package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SchMapper005 {
    int insertSingleCalendar(SchRqDto005 schRqDto005) throws Exception;

    int findCurrentCalValue() throws Exception;
    int insertCalOwner(SchRqDto005 schRqDto005) throws Exception;
    int insertCalManager(List<SchRqDto005.Manager> managers) throws Exception;
}
