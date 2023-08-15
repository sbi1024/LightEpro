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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto001 modifySingleDept(EmpRqDto001 empRqDto001) throws Exception {
        // method start log
        log.info("modifySingleDept Method Start !!!");
        log.info("modifySingleDept Method Request Data : " + empRqDto001);

        // 부서의 정보를 변경한다.
        int modifyDeptInfoCnt = modifyDeptInfo(empRqDto001);
        // 매핑 부서의 정보를 변경한다. (modifyMappingDeptInfo(empRqDto001))
        int modifyMappingDeptInfoCnt = 0;

        // empRsDto001 객체 builder 패턴을 통해 객체 생성
        EmpRsDto001 empRsDto001 = EmpRsDto001.builder()
                .deptSeq(empRqDto001.getDept().getDeptSeq())
                .modifyDeptInfoCnt(modifyDeptInfoCnt)
                .modifyMappingDeptInfoCnt(modifyMappingDeptInfoCnt)
                .build();

        // method end log
        log.info("modifySingleDept Method Return Data : " + empRsDto001);
        log.info("modifySingleDept Method End !!!");

        // return
        return empRsDto001;
    }

    @Override
    public int modifyDeptInfo(EmpRqDto001 empRqDto001) throws Exception {
        // method start log
        log.info("modifyDeptInfo Method Start !!!");
        log.info("modifyDeptInfo Method Request Data : " + empRqDto001);

        int updateDeptInfoCnt = empMapper001.updateDeptInfo(empRqDto001);

        // method end log
        log.info("modifyDeptInfo Method Return Data : " + updateDeptInfoCnt);
        log.info("modifyDeptInfo Method End !!!");

        // return
        return updateDeptInfoCnt;
    }

    @Override
    public int modifyMappingDeptInfo(EmpRqDto001 empRqDto001) throws Exception {
        // method start log
        log.info("modifyMappingDeptInfo Method Start !!!");
        log.info("modifyMappingDeptInfo Method Request Data : " + empRqDto001);

        int updateMappingDeptInfoCnt = empMapper001.updateMappingDeptInfo(empRqDto001);

        // method end log
        log.info("modifyMappingDeptInfo Method Return Data : " + updateMappingDeptInfoCnt);
        log.info("modifyMappingDeptInfo Method End !!!");

        // return
        return updateMappingDeptInfoCnt;
    }
}
