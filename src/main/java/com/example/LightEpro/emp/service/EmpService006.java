package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import com.example.LightEpro.emp.dto.emp006.EmpRqDto006;
import com.example.LightEpro.emp.dto.emp006.EmpRsDto006;

public interface EmpService006 {
    EmpRsDto006 findCompanyInfo(EmpRqDto006 empRqDto006) throws Exception;

    EmpRsDto006.Company findCompany(EmpRqDto006 empRqDto006) throws Exception;
}
