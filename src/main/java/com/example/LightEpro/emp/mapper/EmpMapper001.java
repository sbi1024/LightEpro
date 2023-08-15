package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp001.EmpRqDto001;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper001 {
    int updateDeptInfo(EmpRqDto001 empRqDto001) throws Exception;

    int updateMappingDeptInfo(EmpRqDto001 empRqDto001) throws Exception;
}
