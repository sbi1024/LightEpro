package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp004.EmpRqDto004;
import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper004 {
    List<EmpRsDto004.DeptInfo> selectDeptInfoList(EmpRqDto004 empRqDto004) throws Exception;
}
