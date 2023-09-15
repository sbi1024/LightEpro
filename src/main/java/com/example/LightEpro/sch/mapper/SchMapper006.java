package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch006.SchRqDto006;
import com.example.LightEpro.sch.dto.sch006.SchRsDto006;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper006 {
    int selectUserCount(SchRqDto006 schRqDto006) throws Exception;

    int selectCalendarCount(SchRqDto006 schRqDto006) throws Exception;

    SchRsDto006.Calendar selectCalendar(SchRqDto006 schRqDto006) throws Exception;

    SchRsDto006.Owner selectOwner(SchRqDto006 schRqDto006) throws Exception;

    List<SchRsDto006.Manager> selectManagers(SchRqDto006 schRqDto006) throws Exception;

}
