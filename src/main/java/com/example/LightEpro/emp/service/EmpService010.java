package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import com.example.LightEpro.emp.dto.emp010.EmpRsDto010;

public interface EmpService010 {
    EmpRsDto010 createSingleEmp(EmpRqDto010 empRqDto010) throws Exception;
    int createEmp(EmpRqDto010 empRqDto010) throws Exception;
    int createEmpAccount(EmpRqDto010 empRqDto010) throws Exception;
    int createEmpMapping(EmpRqDto010 empRqDto010) throws Exception;
}
