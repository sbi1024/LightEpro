package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp005.EmpRqDto005;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper005 {
    int selectCompanySequence() throws Exception;

    int insertCompany(EmpRqDto005 empRqDto005) throws Exception;
}
