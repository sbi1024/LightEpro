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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto002 modifySingleDept(EmpRqDto002 empRqDto001) throws Exception {
        // method start log
        log.info("modifySingleDept Method Start !!!");
        log.info("modifySingleDept Method Request Data : " + empRqDto001);

        // 부서의 정보를 변경한다.
        int modifyDeptInfoCnt = modifyDeptInfo(empRqDto001);
        // 매핑 부서의 정보를 변경한다. (modifyMappingDeptInfo(empRqDto001))
        int modifyMappingDeptInfoCnt = 0;

        // empRsDto001 객체 builder 패턴을 통해 객체 생성
        EmpRsDto002 empRsDto002 = EmpRsDto002.builder()
                .deptSeq(empRqDto001.getDept().getDeptSeq())
                .modifyDeptInfoCnt(modifyDeptInfoCnt)
                .modifyMappingDeptInfoCnt(modifyMappingDeptInfoCnt)
                .build();

        // method end log
        log.info("modifySingleDept Method Return Data : " + empRsDto002);
        log.info("modifySingleDept Method End !!!");

        // return
        return empRsDto002;
    }

    @Override
    public int modifyDeptInfo(EmpRqDto002 empRqDto002) throws Exception {
        // method start log
        log.info("modifyDeptInfo Method Start !!!");
        log.info("modifyDeptInfo Method Request Data : " + empRqDto002);

        int updateDeptInfoCnt = empMapper002.updateDeptInfo(empRqDto002);

        // method end log
        log.info("modifyDeptInfo Method Return Data : " + updateDeptInfoCnt);
        log.info("modifyDeptInfo Method End !!!");

        // return
        return updateDeptInfoCnt;
    }

    @Override
    public int modifyMappingDeptInfo(EmpRqDto002 empRqDto002) throws Exception {
        // method start log
        log.info("modifyMappingDeptInfo Method Start !!!");
        log.info("modifyMappingDeptInfo Method Request Data : " + empRqDto002);

        int updateMappingDeptInfoCnt = empMapper002.updateMappingDeptInfo(empRqDto002);

        // method end log
        log.info("modifyMappingDeptInfo Method Return Data : " + updateMappingDeptInfoCnt);
        log.info("modifyMappingDeptInfo Method End !!!");

        // return
        return updateMappingDeptInfoCnt;
    }
}
