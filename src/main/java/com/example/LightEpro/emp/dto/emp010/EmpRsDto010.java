package com.example.LightEpro.emp.dto.emp010;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EmpRsDto010 {
    private int empSeq;
    private int createEmpCnt;
    private int createEmpAccountCnt;
    private int createEmpMappingCnt;
}
