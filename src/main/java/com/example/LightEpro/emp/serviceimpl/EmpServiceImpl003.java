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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto003 removeSingleDept(EmpRqDto003 empRqDto002) throws Exception {
        // method start log
        log.info("removeSingleDept Method Start !!!");
        log.info("removeSingleDept Method Request Data : " + empRqDto002);

        // 부서 정보를 삭제한다.
        int removeDeptInfoCnt = removeDeptInfo(empRqDto002);
        // 매핑 부서의 정보를 삭제한다. (removeMappingDeptInfo(empRqDto002))
        int removeMappingDeptInfoCnt = 0;

        // empRsDto002 객체 builder 패턴을 통해 객체 생성
        EmpRsDto003 empRsDto002 = EmpRsDto003.builder()
                .deptSeq(empRqDto002.getDept().getDeptSeq())
                .removeDeptInfoCnt(removeDeptInfoCnt)
                .removeMappingDeptInfoCnt(removeMappingDeptInfoCnt)
                .build();

        // method end log
        log.info("removeSingleDept Method Return Data : " + empRsDto002);
        log.info("removeSingleDept Method End !!!");

        // return
        return empRsDto002;
    }

    @Override
    public int removeDeptInfo(EmpRqDto003 empRqDto003) throws Exception {
        // method start log
        log.info("removeDeptInfo Method Start !!!");
        log.info("removeDeptInfo Method Request Data : " + empRqDto003);

        int updateDeptInfoCnt = empMapper003.updateDeptInfo(empRqDto003);

        // method end log
        log.info("removeDeptInfo Method Return Data : " + updateDeptInfoCnt);
        log.info("removeDeptInfo Method End !!!");

        // return
        return updateDeptInfoCnt;
    }

    @Override
    public int removeMappingDeptInfo(EmpRqDto003 empRqDto003) throws Exception {
        // method start log
        log.info("removeMappingDeptInfo Method Start !!!");
        log.info("removeMappingDeptInfo Method Request Data : " + empRqDto003);

        int updateMappingDeptInfoCnt = empMapper003.updateMappingDeptInfo(empRqDto003);

        // method end log
        log.info("removeMappingDeptInfo Method Return Data : " + updateMappingDeptInfoCnt);
        log.info("removeMappingDeptInfo Method End !!!");

        // return
        return updateMappingDeptInfoCnt;
    }
}
