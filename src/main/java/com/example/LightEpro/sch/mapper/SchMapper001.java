package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch001.SchRsDto001;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper001 {
    int selectUserCount(SchRqDto001 schRqDto001) throws Exception;

    int selectScheduleCount(SchRqDto001 schRqDto001) throws Exception;

    SchRsDto001.Schedule selectSchedule(SchRqDto001 schRqDto001) throws Exception;

    List<SchRsDto001.Participant> selectParticipants(SchRqDto001 schRqDto001) throws Exception;

    List<SchRsDto001.DisclosureScope> selectDisclosureScopes(SchRqDto001 schRqDto001) throws Exception;

}
