package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper000 {
    int selectDepartmentSequence() throws Exception;
    int insertDepartment(EmpRqDto000 empRqDto000) throws Exception;
}
