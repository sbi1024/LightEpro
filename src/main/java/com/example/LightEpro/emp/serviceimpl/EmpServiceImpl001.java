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

    // EmpMapper003 선언
    private final EmpMapper001 empMapper001;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto001 findSingleDept(EmpRqDto001 empRqDto001) throws Exception {
        // method start log
        log.info("findSingleDept Method Start !!!");
        log.info("findSingleDept Method Request Data : " + empRqDto001);

        // 부서 정보를 조회한다.
        EmpRsDto001.DeptInfo deptInfo = findDeptInfo(empRqDto001);
        // 매핑 부서의 정보를 조회한다.(findMappingDeptInfo(empRqDto003))

        // empRsDto003 객체 builder 패턴을 통해 객체 생성
        EmpRsDto001 empRsDto003 = EmpRsDto001.builder()
                .deptInfo(deptInfo)
                .build();

        // method end log
        log.info("findSingleDept Method Return Data : " + empRsDto003);
        log.info("findSingleDept Method End !!!");

        return empRsDto003;
    }

    @Override
    public EmpRsDto001.DeptInfo findDeptInfo(EmpRqDto001 empRqDto001) throws Exception {
        // method start log
        log.info("findDeptInfo Method Start !!!");
        log.info("findDeptInfo Method Request Data : " + empRqDto001);

        EmpRsDto001.DeptInfo selectDeptInfo = empMapper001.selectDeptInfo(empRqDto001);

        // method end log
        log.info("findDeptInfo Method Return Data : " + selectDeptInfo);
        log.info("findDeptInfo Method End !!!");

        // return
        return selectDeptInfo;
    }
}
