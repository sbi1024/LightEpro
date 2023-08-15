package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp002.EmpRqDto002;
import com.example.LightEpro.emp.dto.emp002.EmpRsDto002;
import com.example.LightEpro.emp.mapper.EmpMapper002;
import com.example.LightEpro.emp.service.EmpService002;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl002 implements EmpService002 {

    // EmpMapper002 선언
    private final EmpMapper002 empMapper002;

    @Override
    public EmpRsDto002 removeSingleDept(EmpRqDto002 empRqDto002) throws Exception {
        // method start log
        log.info("removeSingleDept Method Start !!!");
        log.info("removeSingleDept Method Request Data : " + empRqDto002);

        // 부서 정보를 삭제한다.
        int removeDeptInfoCnt = removeDeptInfo(empRqDto002);
        // 매핑 부서의 정보를 삭제한다. (removeMappingDeptInfo(empRqDto002))
        int removeMappingDeptInfoCnt = 0;

        // empRsDto002 객체 builder 패턴을 통해 객체 생성
        EmpRsDto002 empRsDto002 = EmpRsDto002.builder()
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
    public int removeDeptInfo(EmpRqDto002 empRqDto002) throws Exception {
        // method start log
        log.info("removeDeptInfo Method Start !!!");
        log.info("removeDeptInfo Method Request Data : " + empRqDto002);

        int updateDeptInfoCnt = empMapper002.updateDeptInfo(empRqDto002);

        // method end log
        log.info("removeDeptInfo Method Return Data : " + updateDeptInfoCnt);
        log.info("removeDeptInfo Method End !!!");

        // return
        return updateDeptInfoCnt;
    }

    @Override
    public int removeMappingDeptInfo(EmpRqDto002 empRqDto002) throws Exception {
        // method start log
        log.info("removeMappingDeptInfo Method Start !!!");
        log.info("removeMappingDeptInfo Method Request Data : " + empRqDto002);

        int updateMappingDeptInfoCnt = empMapper002.updateMappingDeptInfo(empRqDto002);

        // method end log
        log.info("removeMappingDeptInfo Method Return Data : " + updateMappingDeptInfoCnt);
        log.info("removeMappingDeptInfo Method End !!!");

        // return
        return updateMappingDeptInfoCnt;
    }
}
