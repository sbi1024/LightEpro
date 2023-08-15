package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;

public interface EmpService003 {

    EmpRsDto003 findSingleDept(EmpRqDto003 empRqDto003) throws Exception;
    EmpRsDto003.DeptInfo findDeptInfo(EmpRqDto003 empRqDto003) throws Exception;
}
