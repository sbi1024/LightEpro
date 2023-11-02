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
    public EmpRsDto005 createCompanyInfo(EmpRqDto005 empRqDto005) throws Exception {
        // method start log
        log.info("createCompanyInfo Method Start !!!");
        log.info("createCompanyInfo Method Request Data : " + empRqDto005);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(empRqDto005);
        // 회사 등록 메소드 호출
        int createCompanyCnt = createCompany(empRqDto005);

        // empRsDto005 객체 builder 패턴을 통해 객체 생성
        EmpRsDto005 empRsDto005 = EmpRsDto005.builder()
                .compSeq(empRqDto005.getComp().getCompSeq())
                .createCompanyCnt(createCompanyCnt)
                .build();

        // method end log
        log.info("createCompanyInfo Method Return Data : " + empRsDto005);
        log.info("createCompanyInfo Method End !!!");

        // return
        return empRsDto005;
    }

    // empRqDto005 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(EmpRqDto005 empRqDto005) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + empRqDto005);
        
        // 회사 시퀀스 값 할당 메소드 호출
        confirmCompanySequence(empRqDto005);

        // method end log
        log.info("assignObject Method End !!!");
    }

    // 회사 객체에 회사 시퀀스 값 할당 메소드
    @Override
    public void confirmCompanySequence(EmpRqDto005 empRqDto005) throws Exception {
        // method start log
        log.info("confirmCompanySequence Method Start !!!");
        log.info("confirmCompanySequence Method Request Data : " + empRqDto005);

        // 회사 객체 생성
        EmpRqDto005.Comp comp = empRqDto005.getComp();
        // 회사 시퀀스 값 채번
        int findCompanySequenceValue = findCompanySequence();
        // comp 객체에 회사 시퀀스 할당
        comp.setCompSeq(findCompanySequenceValue);

        // method end log
        log.info("confirmCompanySequence Method End !!!");
    }

    // 회사 시퀀스 번호 채번 메소드
    @Override
    public int findCompanySequence() throws Exception {
        // method start log
        log.info("findCompanySequence Method Start !!!");

        // 회사 시퀀스 채번
        int selectCompanySequenceValue = empMapper005.selectCompanySequence();

        // method end log
        log.info("findCompanySequence Method Return Data : " + selectCompanySequenceValue);
        log.info("findCompanySequence Method End !!!");

        // return
        return selectCompanySequenceValue;
    }
    
    // 회사 등록 메소드
    @Override
    public int createCompany(EmpRqDto005 empRqDto005) throws Exception {
        // method start log
        log.info("createCompany Method Start !!!");
        log.info("createCompany Method Request Data : " + empRqDto005);

        // 회사 등록 Mapper 호출
        int insertCompCnt = empMapper005.insertCompany(empRqDto005);

        // method end log
        log.info("createCompany Method Return Data : " + empRqDto005);
        log.info("createCompany Method End !!!");

        // return
        return insertCompCnt;
    }
}
