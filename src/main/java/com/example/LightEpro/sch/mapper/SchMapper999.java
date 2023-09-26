package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch999.SchRqDto999;
import com.example.LightEpro.sch.dto.sch999.SchRsDto999;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchMapper999 {
    SchRsDto999 confirmCalendarAuthority(SchRqDto999 schRqDto999) throws Exception;

    SchRsDto999 confirmScheduleAuthority(SchRqDto999 schRqDto999) throws Exception;
}
