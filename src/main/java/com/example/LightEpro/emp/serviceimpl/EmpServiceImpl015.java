package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp015.EmpRqDto015;
import com.example.LightEpro.emp.dto.emp015.EmpRsDto015;
import com.example.LightEpro.emp.mapper.EmpMapper015;
import com.example.LightEpro.emp.service.EmpService015;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl015 implements EmpService015 {

    private final EmpMapper015 empMapper015;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto015 createEmployee(EmpRqDto015 empRqDto015) throws Exception {

        // method start log
        log.info("createEmployee Method Start !!!");
        log.info("createEmployee Method Request Data : " + empRqDto015);

        // 객체 데이터 재 할당
        assignObject(empRqDto015);

        // 사원 정보를 등록한다.
        int createEmpCnt = createEmp(empRqDto015);

        // 사원 계정 정보를 등록한다.
        int createEmpAccountCnt = createEmpAccount(empRqDto015);

        // 사원 매핑 정보를 등록한다.
        int createEmpMappingCnt = createEmpMapping(empRqDto015);

        // empRqDto010 객체 builder 패턴을 통해 객체 생성
        EmpRsDto015 empRsDto015 = EmpRsDto015.builder()
                .empSeq(empRqDto015.getEmp().getEmpSeq())
                .createEmpCnt(createEmpCnt)
                .createEmpAccountCnt(createEmpAccountCnt)
                .createEmpMappingCnt(createEmpMappingCnt)
                .build();


        // method end log
        log.info("createSingleEmp Method Return Data : " + empRsDto015);
        log.info("createSingleEmp Method End !!!");

        // return
        return empRsDto015;
    }

    @Override
    public void assignObject(EmpRqDto015 empRqDto015) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + empRqDto015);

        // empSeq 값을 채번하여 데이터 재 할당을 진행한다.
        int currentEmpValue = findCurrentEmpValue(empRqDto015);
        empRqDto015.getEmp().setEmpSeq(currentEmpValue);

        // 요청값으로 받은 비밀번호를 암호화 하여 재 할당을 진행한다.
        String encodePassWord = encodePassWord(empRqDto015);
        empRqDto015.getEmpAccount().setUserPw(encodePassWord);

        // method end log
        log.info("createSingleEmp Method End !!!");
    }

    @Override
    public int findCurrentEmpValue(EmpRqDto015 empRqDto015) throws Exception {
        // method start log
        log.info("findCurrentEmpValue Method Start !!!");
        log.info("findCurrentEmpValue Method Request Data : " + empRqDto015);

        int currentEmpValue = empMapper015.findCurrentEmpValue();

        // method end log
        log.info("findCurrentEmpValue Method Return Data : " + currentEmpValue);
        log.info("findCurrentEmpValue Method End !!!");

        // return
        return currentEmpValue;
    }

    @Override
    public String encodePassWord(EmpRqDto015 empRqDto015) throws Exception {
        // method start log
        log.info("findCurrentEmpValue Method Start !!!");
        log.info("findCurrentEmpValue Method Request Data : " + empRqDto015);

        String encodePassWord = bCryptPasswordEncoder.encode(empRqDto015.getEmpAccount().getUserPw());

        // method end log
        log.info("findCurrentEmpValue Method Return Data : " + encodePassWord);
        log.info("findCurrentEmpValue Method End !!!");

        return encodePassWord;
    }

    @Override
    public int createEmp(EmpRqDto015 empRqDto015) throws Exception {
        // method start log
        log.info("createEmp Method Start !!!");
        log.info("createEmp Method Request Data : " + empRqDto015);

        int insertEmpInfoCnt = empMapper015.insertEmpInfo(empRqDto015);

        // method end log
        log.info("createEmp Method Return Data : " + insertEmpInfoCnt);
        log.info("createEmp Method End !!!");

        return insertEmpInfoCnt;
    }

    @Override
    public int createEmpAccount(EmpRqDto015 empRqDto015) throws Exception {
        // method start log
        log.info("createEmpAccount Method Start !!!");
        log.info("createEmpAccount Method Request Data : " + empRqDto015);

        int insertEmpAccountInfoCnt = empMapper015.insertAccountInfo(empRqDto015);

        // method end log
        log.info("createEmpAccount Method Return Data : " + insertEmpAccountInfoCnt);
        log.info("createEmpAccount Method End !!!");

        // return
        return insertEmpAccountInfoCnt;
    }

    @Override
    public int createEmpMapping(EmpRqDto015 empRqDto015) throws Exception {
        // method start log
        log.info("createEmpMapping Method Start !!!");
        log.info("createEmpMapping Method Request Data : " + empRqDto015);

        int insertEmpMappingInfoCnt = empMapper015.insertMappingInfo(empRqDto015);

        // method end log
        log.info("createEmpMapping Method Return Data : " + insertEmpMappingInfoCnt);
        log.info("createEmpMapping Method End !!!");

        // return
        return insertEmpMappingInfoCnt;
    }
}
