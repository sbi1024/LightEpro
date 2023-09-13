package com.example.LightEpro.sch.dto.sch007;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchRsDto007 {
    private int calSeq;
    private int modifyCalendarCnt;
    private int modifyOwnerCnt;
    private int createManagersCnt;
    private int modifyManagersCnt;
    private int removeManagersCnt;
}
