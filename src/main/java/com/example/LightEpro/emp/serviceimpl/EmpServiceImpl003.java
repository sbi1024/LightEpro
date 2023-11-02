package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import com.example.LightEpro.emp.mapper.EmpMapper003;
import com.example.LightEpro.emp.service.EmpService003;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl003 implements EmpService003 {

    // EmpMapper002 선언
    private final EmpMapper003 empMapper003;
    
    // 단일 부서 정보 삭제 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto003 removeDepartmentInfo(EmpRqDto003 empRqDto003) throws Exception {
        // method start log
        log.info("removeDepartmentInfo Method Start !!!");
        log.info("removeDepartmentInfo Method Request Data : " + empRqDto003);

        // 부서 삭제 메소드 호출
        int removeDepartmentCnt = removeDepartment(empRqDto003);

        // empRsDto003 객체 builder 패턴을 통해 객체 생성
        EmpRsDto003 empRsDto003 = EmpRsDto003.builder()
                .deptSeq(empRqDto003.getDept().getDeptSeq())
                .removeDepartmentCnt(removeDepartmentCnt)
                .build();

        // method end log
        log.info("removeDepartmentInfo Method Return Data : " + empRsDto003);
        log.info("removeDepartmentInfo Method End !!!");

        // return
        return empRsDto003;
    }

    // 부서 삭제 메소드
    @Override
    public int removeDepartment(EmpRqDto003 empRqDto003) throws Exception {
        // method start log
        log.info("removeDepartment Method Start !!!");
        log.info("removeDepartment Method Request Data : " + empRqDto003);
        
        // 부서 삭제 Mapper 호출
        int updateDepartmentCnt = empMapper003.updateDepartment(empRqDto003);

        // method end log
        log.info("removeDepartment Method Return Data : " + updateDepartmentCnt);
        log.info("removeDepartment Method End !!!");

        // return
        return updateDepartmentCnt;
    }
}
