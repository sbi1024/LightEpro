package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SchMapper002 {
    int selectUserCount(SchRqDto002 schRqDto002) throws Exception;

    String selectCalendarType(SchRqDto002 schRqDto002) throws Exception;

    int selectScheduleCount(SchRqDto002 schRqDto002) throws Exception;

    int selectOriginRegistrant(SchRqDto002 schRqDto002) throws Exception;

    List<SchRqDto002.Participant> selectParticipants(SchRqDto002 schRqDto002) throws Exception;

    List<SchRqDto002.DisclosureScope> selectDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

    int updateSchedule(SchRqDto002 schRqDto002) throws Exception;

    int insertRequestNonMatchParticipants(SchRqDto002 schRqDto002) throws Exception;

    int updateOriginMatchParticipants(SchRqDto002 schRqDto002) throws Exception;

    int deleteOriginNonMatchParticipants(SchRqDto002 schRqDto002) throws Exception;

    int insertRequestNonMatchDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

    int updateOriginMatchDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

    int deleteOriginNonMatchDisclosureScopes(SchRqDto002 schRqDto002) throws Exception;

}
