package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch000.SchRsDto000;

public interface SchService000 {
    int findCurrentSchValue() throws Exception;

    void assignObject(int curSeq, SchRqDto000 schRqDto000) throws Exception;

    int findCalSeq(int calSeq, int cdeSeq) throws Exception;

    SchRsDto000 createSingleSch(SchRqDto000 schRqDto) throws Exception;

    int insertSingleSch(SchRqDto000 schRqDto) throws Exception;

    int insertSchParticipants(SchRqDto000 schRqDto) throws Exception;

    int insertSchDisclosureScopes(SchRqDto000 schRqDto) throws Exception;

    // 캘린더 시퀀스 번호 할당 메소드
    int findCurrentCalValue() throws Exception;

    int insertEcalendar(int cdeSeq) throws Exception;
}
