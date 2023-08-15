package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper003 {
    EmpRsDto003.DeptInfo selectDeptInfo(EmpRqDto003 empRqDto003);
}
