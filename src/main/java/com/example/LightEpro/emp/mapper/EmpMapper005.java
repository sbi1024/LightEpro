package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp005.EmpRqDto005;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper005 {
    int findCurrentCompValue() throws Exception;

    int insertComp(EmpRqDto005 empRqDto005) throws Exception;
}
