package com.example.LightEpro.sch.dto.sch002;

import lombok.*;

@Data
@Builder
public class SchRsDto002 {
    private int schmSeq;
    private int schSeq;
    private int modifyScheduleCnt;
    private int createParticipantsCnt;
    private int modifyParticipantsCnt;
    private int removeParticipantsCnt;
    private int createDisclosureScopesCnt;
    private int modifyDisclosureScopesCnt;
    private int removeDisclosureScopesCnt;
}