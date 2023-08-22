package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import com.example.LightEpro.emp.dto.emp010.EmpRsDto010;
import com.example.LightEpro.emp.mapper.EmpMapper010;
import com.example.LightEpro.emp.service.EmpService010;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl010 implements EmpService010 {

    private final EmpMapper010 empMapper010;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto010 createSingleEmp(EmpRqDto010 empRqDto010) throws Exception {

        // method start log
        log.info("createSingleEmp Method Start !!!");
        log.info("createSingleEmp Method Request Data : " + empRqDto010);

        // 객체 데이터 재 할당
        assignObject(empRqDto010);

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


        // method end log
        log.info("createSingleEmp Method Return Data : " + empRsDto010);
        log.info("createSingleEmp Method End !!!");

        // return
        return empRsDto010;
    }

    @Override
    public void assignObject(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + empRqDto010);

        // empSeq 값을 채번하여 데이터 재 할당을 진행한다.
        int currentEmpValue = findCurrentEmpValue(empRqDto010);
        empRqDto010.getEmp().setEmpSeq(currentEmpValue);

        // 요청값으로 받은 비밀번호를 암호화 하여 재 할당을 진행한다.
        String encodePassWord = encodePassWord(empRqDto010);
        empRqDto010.getEmpAccount().setUserPw(encodePassWord);

        // method end log
        log.info("createSingleEmp Method End !!!");
    }

    @Override
    public int findCurrentEmpValue(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("findCurrentEmpValue Method Start !!!");
        log.info("findCurrentEmpValue Method Request Data : " + empRqDto010);

        int currentEmpValue = empMapper010.findCurrentEmpValue();

        // method end log
        log.info("findCurrentEmpValue Method Return Data : " + currentEmpValue);
        log.info("findCurrentEmpValue Method End !!!");

        // return
        return currentEmpValue;
    }

    @Override
    public String encodePassWord(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("findCurrentEmpValue Method Start !!!");
        log.info("findCurrentEmpValue Method Request Data : " + empRqDto010);

        String encodePassWord = bCryptPasswordEncoder.encode(empRqDto010.getEmpAccount().getUserPw());

        // method end log
        log.info("findCurrentEmpValue Method Return Data : " + encodePassWord);
        log.info("findCurrentEmpValue Method End !!!");

        return encodePassWord;
    }

    @Override
    public int createEmp(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createEmp Method Start !!!");
        log.info("createEmp Method Request Data : " + empRqDto010);

        int insertEmpInfoCnt = empMapper010.insertEmpInfo(empRqDto010);

        // method end log
        log.info("createEmp Method Return Data : " + insertEmpInfoCnt);
        log.info("createEmp Method End !!!");

        return insertEmpInfoCnt;
    }

    @Override
    public int createEmpAccount(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createEmpAccount Method Start !!!");
        log.info("createEmpAccount Method Request Data : " + empRqDto010);

        int insertEmpAccountInfoCnt = empMapper010.insertAccountInfo(empRqDto010);

        // method end log
        log.info("createEmpAccount Method Return Data : " + insertEmpAccountInfoCnt);
        log.info("createEmpAccount Method End !!!");

        // return
        return insertEmpAccountInfoCnt;
    }

    @Override
    public int createEmpMapping(EmpRqDto010 empRqDto010) throws Exception {
        // method start log
        log.info("createEmpMapping Method Start !!!");
        log.info("createEmpMapping Method Request Data : " + empRqDto010);

        int insertEmpMappingInfoCnt = empMapper010.insertMappingInfo(empRqDto010);

        // method end log
        log.info("createEmpMapping Method Return Data : " + insertEmpMappingInfoCnt);
        log.info("createEmpMapping Method End !!!");

        // return
        return insertEmpMappingInfoCnt;
    }
}
