package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch001.SchRsDto001;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchMapper001 {
    SchRsDto001.Sch findSchBySchmSeqAndSchSeq(SchRqDto001 schRqDto001) throws Exception;

    List<SchRsDto001.Participant> findParticipantsBySchmSeqAndSchSeq(SchRqDto001 schRqDto001) throws Exception;

    List<SchRsDto001.DisclosureScope> findDisclosureScopesBySchmSeqAndSchSeq(SchRqDto001 schRqDto001) throws Exception;
}
