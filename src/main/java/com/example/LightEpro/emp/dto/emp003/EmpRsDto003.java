package com.example.LightEpro.emp.dto.emp003;

import com.example.LightEpro.sch.dto.sch001.SchRsDto001;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmpRsDto003 {
    private int deptSeq;
    private int removeDeptInfoCnt;
    private int removeDepartmentCnt;
}
