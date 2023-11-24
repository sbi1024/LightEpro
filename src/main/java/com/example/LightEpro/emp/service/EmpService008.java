package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp008.EmpRqDto008;
import com.example.LightEpro.emp.dto.emp008.EmpRsDto008;

public interface EmpService008 {
    EmpRsDto008 removeCompanyInfo(EmpRqDto008 empRqDto008) throws Exception;
    int removeCompany(EmpRqDto008 empRqDto008) throws Exception;
}
