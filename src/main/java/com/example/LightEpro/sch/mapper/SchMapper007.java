package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchMapper007 {
    SchRqDto007.Calender findCreateInfoByCalSeq(SchRqDto007.Calender calender);
}
