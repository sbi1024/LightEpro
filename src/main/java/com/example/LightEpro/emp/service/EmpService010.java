package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import com.example.LightEpro.emp.dto.emp010.EmpRsDto010;

public interface EmpService010 {
    EmpRsDto010 createSinglePosition(EmpRqDto010 empRqDto010) throws Exception;

    void assignObject(EmpRqDto010 empRqDto010) throws Exception;

    int findCurrentPositionValue() throws Exception;

    int createPosition(EmpRqDto010 empRqDto010) throws Exception;
}
