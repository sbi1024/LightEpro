package com.example.LightEpro.sch.dto.sch000;

import lombok.*;

@Data
@Builder
public class SchRsDto000 {
    private int schmSeq;
    private int schSeq;
    private int createScheduleCnt;
    private int createParticipantsCnt;
    private int createDisclosureScopesCnt;
}
