package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp007.EmpRqDto007;
import com.example.LightEpro.emp.dto.emp007.EmpRsDto007;
import com.example.LightEpro.emp.mapper.EmpMapper001;
import com.example.LightEpro.emp.mapper.EmpMapper007;
import com.example.LightEpro.emp.service.EmpService007;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl007 implements EmpService007 {

    // EmpMapper007 선언
    private final EmpMapper007 empMapper007;

    // 단일 회사 정보 수정 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto007 modifyCompanyInfo(EmpRqDto007 empRqDto007) throws Exception {
        // method start log
        log.info("modifyCompanyInfo Method Start !!!");
        log.info("modifyCompanyInfo Method Request Data : " + empRqDto007);

        // 회사의 정보를 변경한다.
        int modifyCompInfoCnt = modifyCompany(empRqDto007);

        // empRsDto007 객체 builder 패턴을 통해 객체 생성
        EmpRsDto007 empRsDto007 = EmpRsDto007.builder()
                .compSeq(empRqDto007.getComp().getCompSeq())
                .modifyCompInfoCnt(modifyCompInfoCnt)
                .build();

        // method end log
        log.info("modifyCompanyInfo Method Return Data : " + empRsDto007);
        log.info("modifyCompanyInfo Method End !!!");
        return empRsDto007;
    }

    // 회사 수정 메소드
    @Override
    public int modifyCompany(EmpRqDto007 empRqDto007) throws Exception {
        // method start log
        log.info("modifyCompany Method Start !!!");
        log.info("modifyCompany Method Request Data : " + empRqDto007);

        int updateCompanyCnt = empMapper007.updateCompany(empRqDto007);

        // method end log
        log.info("modifyCompany Method Return Data : " + updateCompanyCnt);
        log.info("modifyCompany Method End !!!");

        // return
        return updateCompanyCnt;
    }
}
