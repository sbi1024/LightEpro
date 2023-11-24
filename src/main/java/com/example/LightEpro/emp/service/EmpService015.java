package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp015.EmpRqDto015;
import com.example.LightEpro.emp.dto.emp015.EmpRsDto015;

public interface EmpService015 {
    EmpRsDto015 createEmployee(EmpRqDto015 empRqDto015) throws Exception;

    void assignObject(EmpRqDto015 empRqDto015) throws Exception;

    int findCurrentEmpValue(EmpRqDto015 empRqDto015) throws Exception;

    String encodePassWord(EmpRqDto015 empRqDto015) throws Exception;

    int createEmp(EmpRqDto015 empRqDto015) throws Exception;

    int createEmpAccount(EmpRqDto015 empRqDto015) throws Exception;

    int createEmpMapping(EmpRqDto015 empRqDto015) throws Exception;
}
