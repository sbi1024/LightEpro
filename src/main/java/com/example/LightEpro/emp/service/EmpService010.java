package com.example.LightEpro.emp.service;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import com.example.LightEpro.emp.dto.emp010.EmpRsDto010;

public interface EmpService010 {
    EmpRsDto010 createPositionInfo(EmpRqDto010 empRqDto010) throws Exception;

    void assignObject(EmpRqDto010 empRqDto010) throws Exception;

    void confirmPositionSequence(EmpRqDto010 empRqDto010) throws Exception;

    int findPositionSequence() throws Exception;

    int createPosition(EmpRqDto010 empRqDto010) throws Exception;
}
