package com.example.LightEpro.sch.mapper;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SCH_MAPPER_000 {
    int findCurrentSchValue();

    int checkRegistrant(int curSeq);

    String checkCalType(int calSeq);

    int checkCalUser(@Param("calSeq")int calSeq,
                     @Param("cdeSeq")int cdeSeq);

    int insertSingleSch(SCH_RQ_DTO_000.Sch sch);

    int insertSchParticipants(List<SCH_RQ_DTO_000.Participant> participants);

    int insertSchDisclosureScopes(List<SCH_RQ_DTO_000.DisclosureScope> disclosureScopes);

    int checkEcalExist(int cdeSeq);

    int insertEcal(@Param("currentCalValue")int currentCalValue,
                   @Param("cdeSeq")int cdeSeq);

    int insertEcalUser(@Param("currentCalValue")int currentCalValue,
                       @Param("cdeSeq")int cdeSeq);

    int findCurrentCalValue();
    int insertSchParticipant(@Param("curSeq")int curSeq,
                             @Param("calSeq")int calSeq,
                             @Param("cdeSeq")int cdeSeq);
}
