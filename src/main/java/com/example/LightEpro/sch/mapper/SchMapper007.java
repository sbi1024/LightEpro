package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper007 {
    SchRqDto007.Calender findCalCreateInfoByCalSeq(SchRqDto007.Calender calender) throws Exception;

    List<SchRqDto007.CalendarUser> findCalUsersInfoByCalSeq(SchRqDto007 schRqDto007) throws Exception;
    int updateSingleCal(SchRqDto007 schRqDto007) throws Exception;
}
