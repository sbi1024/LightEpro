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
    
    // 단일 회사 정보 조회 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto006 findCompanyInfo(EmpRqDto006 empRqDto006) throws Exception {
        // method start log
        log.info("findCompanyInfo Method Start !!!");
        log.info("findCompanyInfo Method Request Data : " + empRqDto006);

        // 회사 정보를 조회한다.
        EmpRsDto006.Company company = findCompany(empRqDto006);

        // empRsDto006 객체 builder 패턴을 통해 객체 생성
        EmpRsDto006 empRsDto006 = EmpRsDto006.builder()
                .company(company)
                .build();

        // method end log
        log.info("findCompanyInfo Method Return Data : " + empRsDto006);
        log.info("findCompanyInfo Method End !!!");

        //return
        return empRsDto006;
    }
    
    // 회사 정보 조회 메소드
    @Override
    public EmpRsDto006.Company findCompany(EmpRqDto006 empRqDto006) throws Exception {
        // method start log
        log.info("findCompany Method Start !!!");
        log.info("findCompany Method Request Data : " + empRqDto006);
        
        // 회사 정보 조회 Mapper 호출
        EmpRsDto006.Company company = empMapper006.selectCompany(empRqDto006);

        // method end log
        log.info("findCompany Method Return Data : " + company);
        log.info("findCompany Method End !!!");

        // return
        return company;
    }
}
