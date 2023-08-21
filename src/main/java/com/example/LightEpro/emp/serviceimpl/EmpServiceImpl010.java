package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import com.example.LightEpro.emp.dto.emp010.EmpRsDto010;
import com.example.LightEpro.emp.mapper.EmpMapper010;
import com.example.LightEpro.emp.service.EmpService010;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl010 implements EmpService010 {

    private final EmpMapper010 empMapper010;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto010 createSingleEmp(EmpRqDto010 empRqDto010) throws Exception {

        // method start log
        log.info("createSingleEmp Method Start !!!");
        log.info("createSingleEmp Method Request Data : " + empRqDto010);

        // 사원 정보를 등록한다.
        int createEmpCnt = createEmp(empRqDto010);

        // 사원 계정 정보를 등록한다.
        int createEmpAccountCnt = createEmpAccount(empRqDto010);

        // 사원 매핑 정보를 등록한다.
        int createEmpMappingCnt = createEmpMapping(empRqDto010);

        // empRqDto010 객체 builder 패턴을 통해 객체 생성
        EmpRsDto010 empRsDto010 = EmpRsDto010.builder()
                .empSeq(empRqDto010.getEmp().getEmpSeq())
                .createEmpCnt(createEmpCnt)
                .createEmpAccountCnt(createEmpAccountCnt)
                .createEmpMappingCnt(createEmpMappingCnt)
                .build();


        // method start log
        log.info("createSingleEmp Method Start !!!");
        log.info("createSingleEmp Method Request Data : " + empRsDto010);

        // return
        return empRsDto010;
    }

    @Override
    public int createEmp(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createEmp Method Start !!!");
        log.info("createEmp Method Request Data : " + empRqDto010);

        int insertEmpInfoCnt = empMapper010.insertEmpInfo(empRqDto010);

        // method start log
        log.info("createEmp Method Start !!!");
        log.info("createEmp Method Request Data : " + insertEmpInfoCnt);

        return insertEmpInfoCnt;
    }

    @Override
    public int createEmpAccount(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createEmpAccount Method Start !!!");
        log.info("createEmpAccount Method Request Data : " + empRqDto010);

        int insertEmpAccountInfoCnt = empMapper010.insertAccountInfo(empRqDto010);

        // method start log
        log.info("createEmpAccount Method Start !!!");
        log.info("createEmpAccount Method Request Data : " + insertEmpAccountInfoCnt);

        return insertEmpAccountInfoCnt;
    }

    @Override
    public int createEmpMapping(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createEmpMapping Method Start !!!");
        log.info("createEmpMapping Method Request Data : " + empRqDto010);

        int insertEmpMappingInfoCnt = empMapper010.insertMappingInfo(empRqDto010);

        // method start log
        log.info("createEmpMapping Method Start !!!");
        log.info("createEmpMapping Method Request Data : " + insertEmpMappingInfoCnt);

        return insertEmpMappingInfoCnt;
    }
}
