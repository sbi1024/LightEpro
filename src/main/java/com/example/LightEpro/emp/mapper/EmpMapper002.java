package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper002 {
    int updateDeptInfo(EmpRqDto002 empRqDto002) throws Exception;
    int updateMappingDeptInfo(EmpRqDto002 empRqDto002) throws Exception;
}
