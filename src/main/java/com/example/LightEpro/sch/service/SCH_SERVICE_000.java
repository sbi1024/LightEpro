package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.sch_000.SCH_RS_DTO_000;

public interface SCH_SERVICE_000 {
    int findCurrentSchValue() throws Exception;

    void assignObject(int curSeq, SCH_RQ_DTO_000 schRqDto000) throws Exception;

    int findCalSeq(int calSeq, int cdeSeq) throws Exception;

    SCH_RS_DTO_000 createSingleSch(SCH_RQ_DTO_000 schRqDto) throws Exception;

    int insertSingleSch(SCH_RQ_DTO_000 schRqDto) throws Exception;

    int insertSchParticipants(SCH_RQ_DTO_000 schRqDto) throws Exception;

    int insertSchDisclosureScopes(SCH_RQ_DTO_000 schRqDto) throws Exception;

    // 캘린더 시퀀스 번호 할당 메소드
    int findCurrentCalValue() throws Exception;

    int insertEcalendar(int cdeSeq) throws Exception;
}
