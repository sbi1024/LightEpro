package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp001.EmpRqDto001;
import com.example.LightEpro.emp.dto.emp001.EmpRsDto001;

public interface EmpService001 {
    EmpRsDto001 modifySingleDept(EmpRqDto001 empRqDto001) throws Exception;

    int modifyDeptInfo(EmpRqDto001 empRqDto001) throws Exception;

    int modifyMappingDeptInfo(EmpRqDto001 empRqDto001) throws Exception;
}
