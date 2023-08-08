package com.example.LightEpro.sch.dto.sch007;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchRsDto007 {
    private int calSeq;
    private int updateSingleCalCnt;
    private int removeCalManagersCnt;
    private int modifyCalManagersCnt;
    private int createCalManagersCnt;
}
