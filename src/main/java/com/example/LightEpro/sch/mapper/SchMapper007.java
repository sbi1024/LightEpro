package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper007 {

    String selectCalendarType(SchRqDto007 schRqDto007) throws Exception;

    List<SchRqDto007.Manager> selectManagers(SchRqDto007 schRqDto007) throws Exception;

    int updateCalendar(SchRqDto007 schRqDto007) throws Exception;

    int updateOwner(SchRqDto007 schRqDto007) throws Exception;

    int insertRequestNonMatchManagers(SchRqDto007 schRqDto007) throws Exception;

    int updateOriginMatchManagers(SchRqDto007 schRqDto007) throws Exception;

    int updateNonMatchManagers(SchRqDto007 schRqDto007) throws Exception;


}
