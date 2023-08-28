package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp015.EmpRqDto015;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper015 {
    int findCurrentEmpValue() throws Exception;

    int insertEmpInfo(EmpRqDto015 empRqDto015) throws Exception;

    int insertAccountInfo(EmpRqDto015 empRqDto015) throws Exception;

    int insertMappingInfo(EmpRqDto015 empRqDto015) throws Exception;

}
