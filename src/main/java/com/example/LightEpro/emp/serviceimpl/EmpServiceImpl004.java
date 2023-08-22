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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto004 findDeptList(EmpRqDto004 empRqDto004) throws Exception {
        // method start log
        log.info("findDeptList Method Start !!!");
        log.info("findDeptList Method Request Data : " + empRqDto004);

        // 부서 조직도 목록 리스트를 조회한다.
        List<EmpRsDto004.DeptInfo> findDeptInfoList = findDeptInfoList(empRqDto004);

        // empRsDto004 객체 builder 패턴을 통해 객체 생성
        EmpRsDto004 empRsDto004 = EmpRsDto004.builder()
                .deptInfos(findDeptInfoList)
                .build();

        // method end log
        log.info("findDeptList Method Return Data : " + empRsDto004);
        log.info("findDeptList Method End !!!");

        // return
        return empRsDto004;
    }

    @Override
    public List<EmpRsDto004.DeptInfo> findDeptInfoList(EmpRqDto004 empRqDto004) throws Exception {
        // method start log
        log.info("findDeptInfoList Method Start !!!");
        log.info("findDeptInfoList Method Request Data : " + empRqDto004);

        List<EmpRsDto004.DeptInfo> selectDeptInfoList = empMapper004.selectDeptInfoList(empRqDto004);

        // method end log
        log.info("findDeptInfoList Method Return Data : " + selectDeptInfoList);
        log.info("findDeptInfoList Method End !!!");

        return selectDeptInfoList;
    }
}
