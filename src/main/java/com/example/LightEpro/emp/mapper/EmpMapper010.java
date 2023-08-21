package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper010 {
    int insertEmpInfo(EmpRqDto010 empRqDto010) throws Exception;
    int insertAccountInfo(EmpRqDto010 empRqDto010) throws Exception;
    int insertMappingInfo(EmpRqDto010 empRqDto010) throws Exception;
}
