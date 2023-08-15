package com.example.LightEpro.emp.dto.emp002;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpRsDto002 {
    private int deptSeq;
    private int removeDeptInfoCnt;
    private int removeMappingDeptInfoCnt;
}
