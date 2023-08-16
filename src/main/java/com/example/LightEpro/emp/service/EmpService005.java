package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp005.EmpRqDto005;
import com.example.LightEpro.emp.dto.emp005.EmpRsDto005;

public interface EmpService005 {
    EmpRsDto005 createSingleComp(EmpRqDto005 empRqDto005) throws Exception;

    void assignObject(EmpRqDto005 empRqDto005) throws Exception;

    int findCurrentCompValue() throws Exception;

    int createCompany(EmpRqDto005 empRqDto005) throws Exception;
}
