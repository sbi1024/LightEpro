package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper007 {
    List<SchRqDto007.CalendarUser> findCalUsersInfoByCalSeq(SchRqDto007 schRqDto007) throws Exception;

    int updateCalDetailInfo(SchRqDto007 schRqDto007) throws Exception;

    int updateCalOwner(SchRqDto007 schRqDto007);

    int updateNonMatchManagers(SchRqDto007 schRqDto007) throws Exception;

    int updateOriginMatchManagers(SchRqDto007 schRqDto007) throws Exception;

    int insertNewNonMatchManagers(SchRqDto007 schRqDto007) throws Exception;


}
