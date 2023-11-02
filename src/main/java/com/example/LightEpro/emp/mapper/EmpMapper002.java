package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper002 {
    int updateDepartment(EmpRqDto002 empRqDto002) throws Exception;

    int updateMappingDepartment(EmpRqDto002 empRqDto002) throws Exception;
}
