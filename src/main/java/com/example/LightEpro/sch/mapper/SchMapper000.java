package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchMapper000 {
    String checkCalType(int calSeq) throws Exception;

    int findCurrentSchValue() throws Exception;

    int insertSingleSch(SchRqDto000 schRqDto000) throws Exception;

    int insertSchParticipants(SchRqDto000 schRqDto000) throws Exception;

    int insertSchDisclosureScopes(SchRqDto000 schRqDto000) throws Exception;
}
