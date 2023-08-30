package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SchMapper002 {
    String selectCalendarType(SchRqDto002 schRqDto002) throws Exception;
    int selectScheduleCount(SchRqDto002 schRqDto002) throws Exception;



    List<SchRqDto002.ScheduleUser> selectParticipants(SchRqDto002 schRqDto002) throws Exception;
    List<SchRqDto002.ScheduleUser> selectDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;





    int updateSchedule(SchRqDto002 schRqDto002) throws Exception;

    int insertParticipants(SchRqDto002 schRqDto002) throws Exception;

    int insertDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

}
