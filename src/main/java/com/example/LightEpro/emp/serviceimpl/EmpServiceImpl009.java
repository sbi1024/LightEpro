package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;
import com.example.LightEpro.emp.dto.emp009.EmpRqDto009;
import com.example.LightEpro.emp.dto.emp009.EmpRsDto009;
import com.example.LightEpro.emp.mapper.EmpMapper004;
import com.example.LightEpro.emp.mapper.EmpMapper009;
import com.example.LightEpro.emp.service.EmpService009;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl009 implements EmpService009 {

    // EmpMapper009 선언
    private final EmpMapper009 empMapper009;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto009 findCompList(EmpRqDto009 empRqDto009) throws Exception {

        // method start log
        log.info("findCompList Method Start !!!");
        log.info("findCompList Method Request Data : " + empRqDto009);

        // 부서 조직도 목록 리스트를 조회한다.
        List<EmpRsDto009.CompInfo> findDeptInfoList = findCompInfoList(empRqDto009);

        // empRqDto009 객체 builder 패턴을 통해 객체 생성
        EmpRsDto009 empRsDto009 = EmpRsDto009.builder()
                .compInfos(findDeptInfoList)
                .build();

        // method end log
        log.info("findCompList Method Return Data : " + empRsDto009);
        log.info("findCompList Method End !!!");

        // return
        return empRsDto009;
    }

    @Override
    public List<EmpRsDto009.CompInfo> findCompInfoList(EmpRqDto009 empRqDto009) throws Exception {
        // method start log
        log.info("findCompInfoList Method Start !!!");
        log.info("findCompInfoList Method Request Data : " + empRqDto009);

        List<EmpRsDto009.CompInfo> selectCompInfoList = empMapper009.selectCompInfoList(empRqDto009);

        // method end log
        log.info("findCompInfoList Method Return Data : " + selectCompInfoList);
        log.info("findCompInfoList Method End !!!");

        return selectCompInfoList;
    }
}
