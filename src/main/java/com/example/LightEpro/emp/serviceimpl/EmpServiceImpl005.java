package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp005.EmpRqDto005;
import com.example.LightEpro.emp.dto.emp005.EmpRsDto005;
import com.example.LightEpro.emp.mapper.EmpMapper005;
import com.example.LightEpro.emp.service.EmpService005;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl005 implements EmpService005 {

    // EmpMapper005 선언
    private final EmpMapper005 empMapper005;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto005 createSingleComp(EmpRqDto005 empRqDto005) throws Exception {
        // method start log
        log.info("createSingleComp Method Start !!!");
        log.info("createSingleComp Method Request Data : " + empRqDto005);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(empRqDto005);
        // 부서 등록 메소드 호출
        int createCompanyCnt = createCompany(empRqDto005);

        // empRsDto005 객체 builder 패턴을 통해 객체 생성
        EmpRsDto005 empRsDto005 = EmpRsDto005.builder()
                .compSeq(empRqDto005.getComp().getCompSeq())
                .createCompanyCnt(createCompanyCnt)
                .build();

        // method end log
        log.info("createSingleComp Method Return Data : " + empRsDto005);
        log.info("createSingleComp Method End !!!");

        // return
        return empRsDto005;
    }

    @Override
    public void assignObject(EmpRqDto005 empRqDto005) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + empRqDto005);

        // 부서 시퀀스 할당
        empRqDto005.getComp().setCompSeq(findCurrentCompValue());

        // method end log
        log.info("assignObject Method End !!!");
    }

    @Override
    public int findCurrentCompValue() throws Exception {
        // method start log
        log.info("findCurrentCompValue Method Start !!!");

        // 부서 시퀀스 채번
        int currentCompValue = empMapper005.findCurrentCompValue();

        // method end log
        log.info("findCurrentCompValue Method Return Data : " + currentCompValue);
        log.info("findCurrentCompValue Method End !!!");

        // return
        return currentCompValue;
    }

    @Override
    public int createCompany(EmpRqDto005 empRqDto005) throws Exception {
        // method start log
        log.info("createCompany Method Start !!!");
        log.info("createCompany Method Request Data : " + empRqDto005);

        // 부서 생성
        int insertDeptCnt = empMapper005.insertComp(empRqDto005);

        // method end log
        log.info("createCompany Method Return Data : " + empRqDto005);
        log.info("createCompany Method End !!!");

        // return
        return insertDeptCnt;
    }
}
