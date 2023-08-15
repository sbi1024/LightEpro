package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import com.example.LightEpro.emp.dto.emp002.EmpRsDto002;

public interface EmpService002 {
    EmpRsDto002 removeSingleDept(EmpRqDto002 empRqDto002) throws Exception;
    int removeDeptInfo(EmpRqDto002 empRqDto002) throws Exception;
    int removeMappingDeptInfo(EmpRqDto002 empRqDto002) throws Exception;
}
