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

    // EmpMapper002 선언
    private final EmpMapper008 empMapper008;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto008 removeSingleComp(EmpRqDto008 empRqDto008) throws Exception {
        // method start log
        log.info("removeSingleComp Method Start !!!");
        log.info("removeSingleComp Method Request Data : " + empRqDto008);

        // 부서 정보를 삭제한다.
        int removeCompInfoCnt = removeCompInfo(empRqDto008);
        // 매핑 부서의 정보를 삭제한다. (removeMappingDeptInfo(empRqDto002))
        int removeMappingCompInfoCnt = 0;

        // empRsDto008 객체 builder 패턴을 통해 객체 생성
        EmpRsDto008 empRsDto008 = EmpRsDto008.builder()
                .compSeq(empRqDto008.getComp().getCompSeq())
                .removeCompInfoCnt(removeCompInfoCnt)
                .removeMappingCompInfoCnt(removeMappingCompInfoCnt)
                .build();

        // method end log
        log.info("removeSingleComp Method Return Data : " + empRsDto008);
        log.info("removeSingleComp Method End !!!");

        // return
        return empRsDto008;
    }

    @Override
    public int removeCompInfo(EmpRqDto008 empRqDto008) throws Exception {
        // method start log
        log.info("removeCompInfo Method Start !!!");
        log.info("removeCompInfo Method Request Data : " + empRqDto008);

        int updateCompInfoCnt = empMapper008.updateCompInfo(empRqDto008);

        // method end log
        log.info("removeCompInfo Method Return Data : " + updateCompInfoCnt);
        log.info("removeCompInfo Method End !!!");

        // return
        return updateCompInfoCnt;
    }

    @Override
    public int removeMappingCompInfo(EmpRqDto008 empRqDto008) throws Exception {
        // method start log
        log.info("removeMappingCompInfo Method Start !!!");
        log.info("removeMappingCompInfo Method Request Data : " + empRqDto008);

        int updateMappingCompInfoCnt = empMapper008.updateMappingCompInfo(empRqDto008);

        // method end log
        log.info("removeMappingCompInfo Method Return Data : " + updateMappingCompInfoCnt);
        log.info("removeMappingCompInfo Method End !!!");

        // return
        return updateMappingCompInfoCnt;
    }
}
