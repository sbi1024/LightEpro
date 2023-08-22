package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import com.example.LightEpro.emp.dto.emp008.EmpRqDto008;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper008 {
    int updateCompInfo(EmpRqDto008 empRqDto008) throws Exception;
    int updateMappingCompInfo(EmpRqDto008 empRqDto008) throws Exception;
}
