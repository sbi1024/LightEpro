package com.example.LightEpro.emp.dto.emp002;

import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpRsDto002 {
    private int deptSeq;
    private int modifyDeptInfoCnt;
    private int modifyMappingDeptInfoCnt;
}
