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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto007 modifySingleComp(EmpRqDto007 empRqDto007) throws Exception {
        // method start log
        log.info("modifySingleComp Method Start !!!");
        log.info("modifySingleComp Method Request Data : " + empRqDto007);

        // 회사의 정보를 변경한다.
        int modifyCompInfoCnt = modifyCompInfo(empRqDto007);
        // 매핑 회사의 정보를 변경한다. (modifyMappingCompInfo(empRqDto001))
        int modifyMappingCompInfoCnt = 0;

        // empRsDto007 객체 builder 패턴을 통해 객체 생성
        EmpRsDto007 empRsDto007 = EmpRsDto007.builder()
                .compSeq(empRqDto007.getComp().getCompSeq())
                .modifyCompInfoCnt(modifyCompInfoCnt)
                .modifyMappingCompInfoCnt(modifyMappingCompInfoCnt)
                .build();

        // method end log
        log.info("modifySingleComp Method Return Data : " + empRsDto007);
        log.info("modifySingleComp Method End !!!");
        return empRsDto007;
    }

    @Override
    public int modifyCompInfo(EmpRqDto007 empRqDto007) throws Exception {
        // method start log
        log.info("modifyCompInfo Method Start !!!");
        log.info("modifyCompInfo Method Request Data : " + empRqDto007);

        int updateCompInfoCnt = empMapper007.updateCompInfo(empRqDto007);

        // method end log
        log.info("modifyCompInfo Method Return Data : " + updateCompInfoCnt);
        log.info("modifyCompInfo Method End !!!");

        // return
        return updateCompInfoCnt;
    }

    @Override
    public int modifyMappingCompInfo(EmpRqDto007 empRqDto007) throws Exception {
        // method start log
        log.info("modifyMappingCompInfo Method Start !!!");
        log.info("modifyMappingCompInfo Method Request Data : " + empRqDto007);

        int updateMappingCompInfoCnt = empMapper007.updateMappingCompInfo(empRqDto007);

        // method end log
        log.info("modifyMappingCompInfo Method Return Data : " + updateMappingCompInfoCnt);
        log.info("modifyMappingCompInfo Method End !!!");

        // return
        return updateMappingCompInfoCnt;
    }
}
