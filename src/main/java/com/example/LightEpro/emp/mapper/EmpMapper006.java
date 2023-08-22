package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp006.EmpRqDto006;
import com.example.LightEpro.emp.dto.emp006.EmpRsDto006;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper006 {
    EmpRsDto006.CompInfo selectCompInfo(EmpRqDto006 empRqDto006) throws Exception;
}
