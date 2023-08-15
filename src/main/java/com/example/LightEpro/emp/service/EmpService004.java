package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp004.EmpRqDto004;
import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;

import java.util.List;

public interface EmpService004 {
    EmpRsDto004 findDeptList(EmpRqDto004 empRqDto004) throws Exception;

    List<EmpRsDto004.DeptInfo> findDeptInfoList(EmpRqDto004 empRqDto004) throws Exception;
}
