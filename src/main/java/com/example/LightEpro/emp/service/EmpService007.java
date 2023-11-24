package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp007.EmpRqDto007;
import com.example.LightEpro.emp.dto.emp007.EmpRsDto007;

public interface EmpService007 {
    EmpRsDto007 modifyCompanyInfo(EmpRqDto007 empRqDto007) throws Exception;

    int modifyCompany(EmpRqDto007 empRqDto007) throws Exception;
}
