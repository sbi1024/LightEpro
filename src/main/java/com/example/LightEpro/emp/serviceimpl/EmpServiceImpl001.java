package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp001.EmpRqDto001;
import com.example.LightEpro.emp.dto.emp001.EmpRsDto001;
import com.example.LightEpro.emp.mapper.EmpMapper000;
import com.example.LightEpro.emp.mapper.EmpMapper001;
import com.example.LightEpro.emp.service.EmpService001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl001 implements EmpService001 {

    // EmpMapper001 선언
    private final EmpMapper001 empMapper001;

    // 단일 부서 정보 조회 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto001 findDepartmentInfo(EmpRqDto001 empRqDto001) throws Exception {
        // method start log
        log.info("findDepartmentInfo Method Start !!!");
        log.info("findDepartmentInfo Method Request Data : " + empRqDto001);

        // 단일 부서 조회 메소드 호출
        EmpRsDto001.Department department = findDepartment(empRqDto001);

        // empRsDto001 객체 builder 패턴을 통해 객체 생성
        EmpRsDto001 empRsDto001 = EmpRsDto001.builder()
                .department(department)
                .build();

        // method end log
        log.info("findDepartmentInfo Method Return Data : " + empRsDto001);
        log.info("findDepartmentInfo Method End !!!");

        // return
        return empRsDto001;
    }

    // 부서 조회 메소드
    @Override
    public EmpRsDto001.Department findDepartment(EmpRqDto001 empRqDto001) throws Exception {
        // method start log
        log.info("findDepartment Method Start !!!");
        log.info("findDepartment Method Request Data : " + empRqDto001);

        // 부서 조회 Mapper 호출
        EmpRsDto001.Department department = empMapper001.selectDepartment(empRqDto001);

        // method end log
        log.info("findDepartment Method Return Data : " + department);
        log.info("findDepartment Method End !!!");

        // return
        return department;
    }
}
