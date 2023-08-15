package com.example.LightEpro.emp.dto.emp001;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpRsDto001 {
    private int deptSeq;
    private int modifyDeptInfoCnt;
    private int modifyMappingDeptInfoCnt;
}
