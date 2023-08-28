package com.example.LightEpro.emp.dto.emp015;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EmpRsDto015 {
    private int empSeq;
    private int createEmpCnt;
    private int createEmpAccountCnt;
    private int createEmpMappingCnt;
}
