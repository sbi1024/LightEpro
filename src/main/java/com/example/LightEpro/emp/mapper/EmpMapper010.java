package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper010 {
    int selectPositionSequence() throws Exception;

    int insertPosition(EmpRqDto010 empRqDto010);
}
