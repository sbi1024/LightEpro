package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp004.EmpRqDto004;
import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;

import java.util.List;

public interface EmpService004 {
    EmpRsDto004 findDepartmentsInfo(EmpRqDto004 empRqDto004) throws Exception;

    List<EmpRsDto004.Department> findDepartments(EmpRqDto004 empRqDto004) throws Exception;
}
