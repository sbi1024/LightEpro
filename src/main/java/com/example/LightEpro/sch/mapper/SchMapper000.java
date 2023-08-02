package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchMapper000 {
    int findCurrentSchValue() throws Exception;

    int checkRegistrant(int curSeq) throws Exception;

    String checkCalType(int calSeq) throws Exception;

    int checkCalUser(@Param("calSeq") int calSeq,
                     @Param("cdeSeq") int cdeSeq) throws Exception;

    int insertSingleSch(SchRqDto000.Sch sch) throws Exception;

    int insertSchParticipants(List<SchRqDto000.Participant> participants) throws Exception;

    int insertSchDisclosureScopes(List<SchRqDto000.DisclosureScope> disclosureScopes) throws Exception;

    int checkEcalExist(int cdeSeq) throws Exception;

    int insertEcal(@Param("currentCalValue") int currentCalValue,
                   @Param("cdeSeq") int cdeSeq) throws Exception;

    int insertEcalUser(@Param("currentCalValue") int currentCalValue,
                       @Param("cdeSeq") int cdeSeq) throws Exception;

    int findCurrentCalValue() throws Exception;

    int insertSchParticipant(@Param("curSeq") int curSeq,
                             @Param("calSeq") int calSeq,
                             @Param("cdeSeq") int cdeSeq) throws Exception;
}
