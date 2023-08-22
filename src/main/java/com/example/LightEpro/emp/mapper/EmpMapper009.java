package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp004.EmpRqDto004;
import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;
import com.example.LightEpro.emp.dto.emp009.EmpRqDto009;
import com.example.LightEpro.emp.dto.emp009.EmpRsDto009;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper009 {
    List<EmpRsDto009.CompInfo> selectCompInfoList(EmpRqDto009 empRqDto009) throws Exception;
}
