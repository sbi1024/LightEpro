package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp008.EmpRqDto008;
import com.example.LightEpro.emp.dto.emp008.EmpRsDto008;

public interface EmpService008 {
    EmpRsDto008 removeSingleComp(EmpRqDto008 empRqDto008) throws Exception;
    int removeCompInfo(EmpRqDto008 empRqDto008) throws Exception;
    int removeMappingCompInfo(EmpRqDto008 empRqDto008) throws Exception;
}
