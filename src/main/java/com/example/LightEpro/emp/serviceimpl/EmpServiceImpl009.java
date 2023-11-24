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

    // 회사 조직도 조회 메소드 (하위 회사 포함)
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto009 findCompaniesInfo(EmpRqDto009 empRqDto009) throws Exception {

        // method start log
        log.info("findCompaniesInfo Method Start !!!");
        log.info("findCompaniesInfo Method Request Data : " + empRqDto009);

        // 회사 조직도 목록 리스트를 조회한다.
        List<EmpRsDto009.Company> companies = findCompanies(empRqDto009);
        // 회사 조직도 목록 카운팅
        int companiesCnt = companies == null ? 0 : companies.size();

        // empRqDto009 객체 builder 패턴을 통해 객체 생성
        EmpRsDto009 empRsDto009 = EmpRsDto009.builder()
                .companies(companies)
                .companiesCnt(companiesCnt)
                .build();

        // method end log
        log.info("findCompaniesInfo Method Return Data : " + empRsDto009);
        log.info("findCompaniesInfo Method End !!!");

        // return
        return empRsDto009;
    }

    // 회사 조직도 조회 메소드 (하위 회사 포함)
    @Override
    public List<EmpRsDto009.Company> findCompanies(EmpRqDto009 empRqDto009) throws Exception {
        // method start log
        log.info("findCompanies Method Start !!!");
        log.info("findCompanies Method Request Data : " + empRqDto009);
        
        // 회사 조직도 목록 조회 Mapper 호출
        List<EmpRsDto009.Company> selectCompanies = empMapper009.selectCompanies(empRqDto009);

        // method end log
        log.info("findCompanies Method Return Data : " + selectCompanies);
        log.info("findCompanies Method End !!!");

        // return
        return selectCompanies;
    }
}
