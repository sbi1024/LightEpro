package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp001.EmpRqDto001;
import com.example.LightEpro.emp.dto.emp001.EmpRsDto001;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper001 {
    EmpRsDto001.Department selectDepartment(EmpRqDto001 empRqDto001) throws Exception;
}
