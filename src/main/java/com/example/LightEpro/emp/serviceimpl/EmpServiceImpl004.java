package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp004.EmpRqDto004;
import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;
import com.example.LightEpro.emp.mapper.EmpMapper004;
import com.example.LightEpro.emp.service.EmpService004;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl004 implements EmpService004 {

    // EmpMapper004 선언
    private final EmpMapper004 empMapper004;

    // 부서 조직도 조회 메소드 (하위 부서 포함)
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto004 findDepartmentsInfo(EmpRqDto004 empRqDto004) throws Exception {
        // method start log
        log.info("findDepartmentsInfo Method Start !!!");
        log.info("findDepartmentsInfo Method Request Data : " + empRqDto004);

        // 부서 조직도 목록 조회 메소드 호출
        List<EmpRsDto004.Department> departments = findDepartments(empRqDto004);
        // 부서 조직도 목록 카운팅
        int departmentsCnt = departments == null ? 0 : departments.size();


        // empRsDto004 객체 builder 패턴을 통해 객체 생성
        EmpRsDto004 empRsDto004 = EmpRsDto004.builder()
                .departments(departments)
                .departmentsCnt(departmentsCnt)
                .build();

        // method end log
        log.info("findDepartmentsInfo Method Return Data : " + empRsDto004);
        log.info("findDepartmentsInfo Method End !!!");

        // return
        return empRsDto004;
    }

    // 부서 조직도 목록 조회 메소드 (하위 부서 포함)
    @Override
    public List<EmpRsDto004.Department> findDepartments(EmpRqDto004 empRqDto004) throws Exception {
        // method start log
        log.info("findDepartments Method Start !!!");
        log.info("findDepartments Method Request Data : " + empRqDto004);
        
        // 부서 조직도 목록 조회 Mapper 호출
        List<EmpRsDto004.Department> selectDepartments = empMapper004.selectDepartments(empRqDto004);

        // method end log
        log.info("findDepartments Method Return Data : " + selectDepartments);
        log.info("findDepartments Method End !!!");

        // return
        return selectDepartments;
    }
}
