package com.example.LightEpro.sch.dto.sch008;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchRsDto008 {
    int calSeq;
    int removeCalendarCnt;
    int removeCalendarUsersCnt;
    int removeSchedulesCnt;
    int removeScheduleUsersCnt;
}
