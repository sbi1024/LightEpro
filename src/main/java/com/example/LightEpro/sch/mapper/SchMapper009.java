package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch009.SchRqDto009;
import com.example.LightEpro.sch.dto.sch009.SchRsDto009;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper009 {

    List<SchRsDto009.Calendar> selectCalendarList(SchRqDto009 schRqDto009) throws Exception;

    int selectCalendarListCnt(SchRqDto009 schRqDto009) throws Exception;
}
