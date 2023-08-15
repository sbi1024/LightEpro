package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import com.example.LightEpro.emp.mapper.EmpMapper003;
import com.example.LightEpro.emp.service.EmpService003;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl003 implements EmpService003 {

    // EmpMapper003 선언
    private final EmpMapper003 empMapper003;

    @Override
    public EmpRsDto003 findSingleDept(EmpRqDto003 empRqDto003) throws Exception {
        // method start log
        log.info("findSingleDept Method Start !!!");
        log.info("findSingleDept Method Request Data : " + empRqDto003);

        // 부서 정보를 조회한다.
        EmpRsDto003.DeptInfo deptInfo = findDeptInfo(empRqDto003);
        // 매핑 부서의 정보를 조회한다.(findMappingDeptInfo(empRqDto003))

        // empRsDto003 객체 builder 패턴을 통해 객체 생성
        EmpRsDto003 empRsDto003 = EmpRsDto003.builder()
                .deptInfo(deptInfo)
                .build();

        // method end log
        log.info("findSingleDept Method Return Data : " + empRsDto003);
        log.info("findSingleDept Method End !!!");

        return empRsDto003;
    }

    @Override
    public EmpRsDto003.DeptInfo findDeptInfo(EmpRqDto003 empRqDto003) throws Exception {
        // method start log
        log.info("findDeptInfo Method Start !!!");
        log.info("findDeptInfo Method Request Data : " + empRqDto003);

        EmpRsDto003.DeptInfo selectDeptInfo = empMapper003.selectDeptInfo(empRqDto003);

        // method end log
        log.info("findDeptInfo Method Return Data : " + selectDeptInfo);
        log.info("findDeptInfo Method End !!!");

        // return
        return selectDeptInfo;
    }
}
