package com.example.LightEpro.emp.mapper;

import com.example.LightEpro.emp.dto.emp007.EmpRqDto007;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper007 {
    int updateCompInfo(EmpRqDto007 empRqDto007) throws Exception;

    int updateMappingCompInfo(EmpRqDto007 empRqDto007) throws Exception;
}