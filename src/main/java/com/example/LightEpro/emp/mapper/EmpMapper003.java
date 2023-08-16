package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper003 {
    int updateDeptInfo(EmpRqDto003 empRqDto003) throws Exception;
    int updateMappingDeptInfo(EmpRqDto003 empRqDto003) throws Exception;
}
