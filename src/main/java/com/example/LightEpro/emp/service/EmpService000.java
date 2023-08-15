package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp000.EmpRsDto000;
import com.example.LightEpro.sch.dto.sch000.SchRqDto000;

public interface EmpService000 {
    EmpRsDto000 createSingleDept(EmpRqDto000 empRqDto000) throws Exception;

    void assignObject(EmpRqDto000 empRqDto000) throws Exception;

    int findCurrentDeptValue() throws Exception;

    int createDepartment(EmpRqDto000 empRqDto000) throws Exception;
}
