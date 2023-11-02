package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import com.example.LightEpro.emp.dto.emp002.EmpRsDto002;
import com.example.LightEpro.emp.mapper.EmpMapper002;
import com.example.LightEpro.emp.service.EmpService002;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl002 implements EmpService002 {

    // EmpMapper001 선언
    private final EmpMapper002 empMapper002;
    
    // 단일 부서 정보 수정 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto002 modifyDepartmentInfo(EmpRqDto002 empRqDto002) throws Exception {
        // method start log
        log.info("modifyDepartmentInfo Method Start !!!");
        log.info("modifyDepartmentInfo Method Request Data : " + empRqDto002);

        // 부서의 정보를 변경한다.
        int modifyDepartmentCnt = modifyDepartment(empRqDto002);

        // empRsDto002 객체 builder 패턴을 통해 객체 생성
        EmpRsDto002 empRsDto002 = EmpRsDto002.builder()
                .deptSeq(empRqDto002.getDept().getDeptSeq())
                .modifyDepartmentCnt(modifyDepartmentCnt)
                .build();

        // method end log
        log.info("modifyDepartmentInfo Method Return Data : " + empRsDto002);
        log.info("modifyDepartmentInfo Method End !!!");

        // return
        return empRsDto002;
    }
    
    // 부서 수정 메소드
    @Override
    public int modifyDepartment(EmpRqDto002 empRqDto002) throws Exception {
        // method start log
        log.info("modifyDepartment Method Start !!!");
        log.info("modifyDepartment Method Request Data : " + empRqDto002);

        // 부서 수정 Mapper 호출
        int updateDepartmentCnt = empMapper002.updateDepartment(empRqDto002);

        // method end log
        log.info("modifyDepartment Method Return Data : " + updateDepartmentCnt);
        log.info("modifyDepartment Method End !!!");

        // return
        return updateDepartmentCnt;
    }
}
