package com.example.LightEpro.sch.dto.sch007;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchRsDto007 {
    private int calSeq;
    private int deleteCalUsersCnt;
    private int updateSingleCalCnt;
    private int insertCalOwnerCnt;
    private int insertCalManagerCnt;
}
