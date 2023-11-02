package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;

public interface EmpService003 {

    EmpRsDto003 removeDepartmentInfo(EmpRqDto003 empRqDto003) throws Exception;
    int removeDepartment(EmpRqDto003 empRqDto003) throws Exception;
}
