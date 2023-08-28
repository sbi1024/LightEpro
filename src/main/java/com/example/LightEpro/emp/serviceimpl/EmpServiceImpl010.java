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

    // EmpMapper010 선언
    private final EmpMapper010 empMapper010;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto010 createSinglePosition(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createSinglePosition Method Start !!!");
        log.info("createSinglePosition Method Request Data : " + empRqDto010);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(empRqDto010);
        // 직책/직위/직급 등록 메소드 호출
        int createPositionCnt = createPosition(empRqDto010);

        // empRqDto010 객체 builder 패턴을 통해 객체 생성
        EmpRsDto010 empRsDto010 = EmpRsDto010.builder()
                .positionCodeSeq(empRqDto010.getPosition().getPositionCodeSeq())
                .createPositionCnt(createPositionCnt)
                .build();

        // method end log
        log.info("createSinglePosition Method Return Data : " + empRsDto010);
        log.info("createSinglePosition Method End !!!");

        // return
        return empRsDto010;
    }

    @Override
    public void assignObject(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + empRqDto010);

        // 직책/직위/직급 시퀀스 할당
        empRqDto010.getPosition().setPositionCodeSeq(findCurrentPositionValue());

        // method end log
        log.info("assignObject Method End !!!");
    }

    @Override
    public int findCurrentPositionValue() throws Exception {
        // method start log
        log.info("findCurrentPositionValue Method Start !!!");

        // 부서 시퀀스 채번
        int currentPositionValue = empMapper010.findCurrentPositionValue();

        // method end log
        log.info("findCurrentPositionValue Method Return Data : " + currentPositionValue);
        log.info("findCurrentPositionValue Method End !!!");

        // return
        return currentPositionValue;
    }

    @Override
    public int createPosition(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createPositionCode Method Start !!!");
        log.info("createPositionCode Method Request Data : " + empRqDto010);

        int insertPositionCnt = empMapper010.insertPosition(empRqDto010);

        // method end log
        log.info("createPositionCode Method Return Data : " + insertPositionCnt);
        log.info("createPositionCode Method End !!!");

        // return
        return insertPositionCnt;
    }
}
