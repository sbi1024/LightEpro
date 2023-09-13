package com.example.LightEpro.sch.dto.sch005;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchRsDto005 {
    private int calSeq;
    private int createCalendarCnt;
    private int createOwnerCnt;
    private int createManagersCnt;
}
