package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import com.example.LightEpro.emp.dto.emp002.EmpRsDto002;

public interface EmpService002 {
    EmpRsDto002 modifySingleDept(EmpRqDto002 empRqDto001) throws Exception;

    int modifyDeptInfo(EmpRqDto002 empRqDto002) throws Exception;

    int modifyMappingDeptInfo(EmpRqDto002 empRqDto002) throws Exception;
}
