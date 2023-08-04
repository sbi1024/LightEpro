package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SchMapper002 {
    SchRqDto002.Sch findCreateSeqBySchmSeqAndSchSeq(SchRqDto002 schRqDto002) throws Exception;

    int deleteSchUsers(SchRqDto002 schRqDto002) throws Exception;

    int updateSingleSch(SchRqDto002 schRqDto002) throws Exception;

    int insertSchParticipants(List<SchRqDto002.Participant> participants) throws Exception;

    int insertSchDisclosureScopes(List<SchRqDto002.DisclosureScope> disclosureScopes) throws Exception;

    String checkCalType(int calSeq) throws Exception;

    int checkCalUser(@Param("calSeq") int calSeq,
                     @Param("cdeSeq") int cdeSeq) throws Exception;

    int checkEcalExist(int cdeSeq) throws Exception;

    int insertEcal(@Param("currentCalValue") int currentCalValue,
                   @Param("cdeSeq") int cdeSeq) throws Exception;

    int insertEcalUser(@Param("currentCalValue") int currentCalValue,
                       @Param("cdeSeq") int cdeSeq) throws Exception;

    int findCurrentCalValue() throws Exception;

    int checkRegistrant(int curSeq) throws Exception;

    int insertSchParticipant(@Param("curSeq") int curSeq,
                             @Param("calSeq") int calSeq,
                             @Param("cdeSeq") int cdeSeq,
                             @Param("createDate") LocalDateTime createDate,
                             @Param("empSeq") int empSeq) throws Exception;

    int checkSchExist(SchRqDto002 schRqDto002);
}
