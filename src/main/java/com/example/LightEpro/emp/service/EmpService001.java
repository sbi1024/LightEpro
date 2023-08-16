package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp001.EmpRqDto001;
import com.example.LightEpro.emp.dto.emp001.EmpRsDto001;

public interface EmpService001 {
    EmpRsDto001 findSingleDept(EmpRqDto001 empRqDto001) throws Exception;
    EmpRsDto001.DeptInfo findDeptInfo(EmpRqDto001 empRqDto001) throws Exception;
}
