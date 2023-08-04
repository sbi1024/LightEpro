package com.example.LightEpro.sch.service;

import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch002.SchRsDto002;

public interface SchService002 {
    SchRsDto002 modifySingleSch(SchRqDto002 schRqDto002)throws Exception;
    int deleteSchUsers(SchRqDto002 schRqDto002) throws Exception;
    int assignObject(SchRqDto002 schRqDto002) throws Exception;
    int updateSingleSch(SchRqDto002 schRqDto002) throws Exception;

    int insertSchParticipants(SchRqDto002 schRqDto) throws Exception;
    int insertSchDisclosureScopes(SchRqDto002 schRqDto) throws Exception;

    int findCalSeq(int calSeq, int cdeSeq) throws Exception;

    int insertEcalendar(int cdeSeq) throws Exception;
    // 캘린더 시퀀스 번호 할당 메소드
    int findCurrentCalValue() throws Exception;
}
