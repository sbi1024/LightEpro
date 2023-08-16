package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import com.example.LightEpro.emp.dto.emp006.EmpRqDto006;
import com.example.LightEpro.emp.dto.emp006.EmpRsDto006;
import com.example.LightEpro.emp.mapper.EmpMapper003;
import com.example.LightEpro.emp.mapper.EmpMapper006;
import com.example.LightEpro.emp.service.EmpService006;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl006 implements EmpService006 {

    // EmpMapper006 선언
    private final EmpMapper006 empMapper006;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto006 findSingleComp(EmpRqDto006 empRqDto006) throws Exception {
        // method start log
        log.info("findSingleComp Method Start !!!");
        log.info("findSingleComp Method Request Data : " + empRqDto006);

        // 회사 정보를 조회한다.
        EmpRsDto006.CompInfo compInfo = findCompInfo(empRqDto006);
        // 매핑 회사의 정보를 조회한다.(findMappingCompInfo(empRqDto006))

        // empRsDto006 객체 builder 패턴을 통해 객체 생성
        EmpRsDto006 empRsDto006 = EmpRsDto006.builder()
                .compInfo(compInfo)
                .build();

        // method end log
        log.info("findSingleComp Method Return Data : " + empRsDto006);
        log.info("findSingleComp Method End !!!");

        //return
        return empRsDto006;
    }

    @Override
    public EmpRsDto006.CompInfo findCompInfo(EmpRqDto006 empRqDto006) throws Exception {
        // method start log
        log.info("findDeptInfo Method Start !!!");
        log.info("findDeptInfo Method Request Data : " + empRqDto006);

        EmpRsDto006.CompInfo selectCompInfo = empMapper006.selectCompInfo(empRqDto006);

        // method end log
        log.info("findDeptInfo Method Return Data : " + selectCompInfo);
        log.info("findDeptInfo Method End !!!");

        // return
        return selectCompInfo;
    }
}
