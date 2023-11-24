package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp008.EmpRqDto008;
import com.example.LightEpro.emp.dto.emp008.EmpRsDto008;
import com.example.LightEpro.emp.mapper.EmpMapper008;
import com.example.LightEpro.emp.service.EmpService008;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl008 implements EmpService008 {

    // empMapper008 선언
    private final EmpMapper008 empMapper008;

    // 단일 회사 정보 삭제 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto008 removeCompanyInfo(EmpRqDto008 empRqDto008) throws Exception {
        // method start log
        log.info("removeCompanyInfo Method Start !!!");
        log.info("removeCompanyInfo Method Request Data : " + empRqDto008);

        // 회사 정보를 삭제한다.
        int removeCompanyCnt = removeCompany(empRqDto008);

        // empRsDto008 객체 builder 패턴을 통해 객체 생성
        EmpRsDto008 empRsDto008 = EmpRsDto008.builder()
                .compSeq(empRqDto008.getComp().getCompSeq())
                .removeCompanyCnt(removeCompanyCnt)
                .build();

        // method end log
        log.info("removeCompanyInfo Method Return Data : " + empRsDto008);
        log.info("removeCompanyInfo Method End !!!");

        // return
        return empRsDto008;
    }

    // 회사 삭제 메소드
    @Override
    public int removeCompany(EmpRqDto008 empRqDto008) throws Exception {
        // method start log
        log.info("removeCompany Method Start !!!");
        log.info("removeCompany Method Request Data : " + empRqDto008);

        // 회사 삭제 Mapper 호출
        int updateCompanyCnt = empMapper008.updateCompany(empRqDto008);

        // method end log
        log.info("removeCompany Method Return Data : " + updateCompanyCnt);
        log.info("removeCompany Method End !!!");

        // return
        return updateCompanyCnt;
    }
}
