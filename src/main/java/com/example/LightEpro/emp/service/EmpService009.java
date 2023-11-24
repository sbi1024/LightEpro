package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp004.EmpRqDto004;
import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;
import com.example.LightEpro.emp.dto.emp009.EmpRqDto009;
import com.example.LightEpro.emp.dto.emp009.EmpRsDto009;

import java.util.List;

public interface EmpService009 {
    EmpRsDto009 findCompaniesInfo(EmpRqDto009 empRqDto009) throws Exception;

    List<EmpRsDto009.Company> findCompanies(EmpRqDto009 empRqDto009) throws Exception;
}
