package com.example.LightEpro.emp.serviceimpl;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp000.EmpRsDto000;
import com.example.LightEpro.emp.mapper.EmpMapper000;
import com.example.LightEpro.emp.service.EmpService000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpServiceImpl000 implements EmpService000 {

    private final EmpMapper000 empMapper000;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public EmpRsDto000 createSingleDept(EmpRqDto000 empRqDto000) throws Exception {
        // method start log
        log.info("createSingleDept Method Start !!!");
        log.info("createSingleDept Method Request Data : " + empRqDto000);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(empRqDto000);
        // 부서 등록 메소드 호출
        int createDepartmentCnt = createDepartment(empRqDto000);
        // 부서 경로 수정 메소드 호출
        int modifyDeptPathCnt = modifyDeptPath(empRqDto000);

        // empRsDto000 객체 builder 패턴을 통해 객체 생성
        EmpRsDto000 empRsDto000 = EmpRsDto000.builder()
                .deptSeq(empRqDto000.getDept().getDeptSeq())
                .createDepartmentCnt(createDepartmentCnt)
                .modifyDeptPathCnt(modifyDeptPathCnt)
                .build();

        // method end log
        log.info("createSingleDept Method Return Data : " + empRsDto000);
        log.info("createSingleDept Method End !!!");
        // return
        return empRsDto000;
    }

    @Override
    public void assignObject(EmpRqDto000 empRqDto000) throws Exception {
        // method start log
        log.info("getDeptPath Method Start !!!");
        log.info("getDeptPath Method Request Data : " + empRqDto000);

        // 부서 시퀀스 할당
        empRqDto000.getDept().setDeptSeq(findCurrentDeptValue());

        // method end log
        log.info("getDeptPath Method End !!!");
    }

    @Override
    public int findCurrentDeptValue() throws Exception {
        // method start log
        log.info("getDeptPath Method Start !!!");

        // 부서 시퀀스 채번
        int currentDeptValue = empMapper000.findCurrentDeptValue();

        // method end log
        log.info("getDeptPath Method Return Data : " + currentDeptValue);
        log.info("getDeptPath Method End !!!");
        // return
        return currentDeptValue;
    }

    @Override
    public int createDepartment(EmpRqDto000 empRqDto000) throws Exception {
        // method start log
        log.info("createDepartment Method Start !!!");
        log.info("createDepartment Method Request Data : " + empRqDto000);

        // 부서 생성
        int insertDeptCnt = empMapper000.insertDept(empRqDto000);

        // method end log
        log.info("createDepartment Method Return Data : " + empRqDto000);
        log.info("createDepartment Method End !!!");
        // return
        return insertDeptCnt;
    }

    @Override
    public int modifyDeptPath(EmpRqDto000 empRqDto000) throws Exception {
        // method start log
        log.info("modifyDeptPath Method Start !!!");
        log.info("modifyDeptPath Method Request Data : " + empRqDto000);

        // 부서 경로값 할당
        empRqDto000.getDept().setDeptPath(getDeptPath(empRqDto000));

        // deptPath 값 update 처리 진행
        int updateDeptPathCnt = empMapper000.updateDeptPath(empRqDto000);

        // method end log
        log.info("createDepartment Method Return Data : " + empRqDto000);
        log.info("createDepartment Method End !!!");
        // return
        return updateDeptPathCnt;
    }

    @Override
    public String getDeptPath(EmpRqDto000 empRqDto000) throws Exception {
        // method start log
        log.info("getDeptPath Method Start !!!");
        log.info("getDeptPath Method Request Data : " + empRqDto000);

        // 부서 객체로 부터 , deptSeq , parentSeq 값 추출
        EmpRqDto000.Dept dept = empRqDto000.getDept();
        int deptSeq = dept.getDeptSeq();
        int parentDeptSeq = dept.getParentDeptSeq();

        // deptPath 추출
        String deptPath;
        if (parentDeptSeq == 0) {
            // 상위 부서 시퀀스값이 존재하지 않는다면 , deptPath 값으로 할당
            deptPath = String.valueOf(deptSeq);
        } else {
            // 상위 부서 시퀀스값이 존재한다면 , 재귀쿼리를 통해 부서 경로 추출하여 할당
            deptPath = empMapper000.getDeptPath(empRqDto000);
        }

        // method end log
        log.info("getDeptPath Method Return Data : " + empRqDto000);
        log.info("getDeptPath Method End !!!");
        // return
        return deptPath;
    }
}
