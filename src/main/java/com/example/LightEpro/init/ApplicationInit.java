package com.example.LightEpro.init;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp000.EmpRsDto000;
import com.example.LightEpro.emp.dto.emp005.EmpRqDto005;
import com.example.LightEpro.emp.dto.emp005.EmpRsDto005;
import com.example.LightEpro.emp.service.EmpService000;
import com.example.LightEpro.emp.service.EmpService005;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationInit implements InitializingBean {

    private final EmpService005 empService005;
    private final EmpService000 empService000;


    // 헤당 메소드는 스프링 부트가 최초 실행이 될때 , 동작하는 메소드이다
    // 초기 작업에 필요한 일련의 작업 메소드를 구성한다.
    @Override
    public void afterPropertiesSet() throws Exception {
        // 차후에 배포시 주석처리하여 배포
        init();
    }

    // init 메소드 구성
    public void init() throws Exception {
        // 1. 회사 더미 데이터 등록
        createCompTempData();
        // 2. 부서 더미 데이터 등록
        createDeptTempData();
        // 3. 직급/직책/직위 더미 데이터 등록
        createPositionTempData();
        // 4. 사원 더미 데이터 등록
        createEmpTempData();
        // 5. 캘린더 더미 데이터 등록
        createCalendarTempData();
        // 6. 일정 더미 데이터 등록
        createSchTempData();
    }

    // 1. 회사 정보 등록 메소드
    public void createCompTempData() throws Exception {
        // method start log
        log.info("createCompTempData Method Start !!!");

        EmpRqDto005 empRqDto005 = new EmpRqDto005();
        EmpRqDto005.Emp emp = new EmpRqDto005.Emp();
        emp.setEmpSeq(1);

        EmpRqDto005.Comp comp = new EmpRqDto005.Comp();
        comp.setCompName("테스트 init 회사");
        comp.setParentCompSeq(0);

        empRqDto005.setEmp(emp);
        empRqDto005.setComp(comp);

        EmpRsDto005 empRsDto005 = empService005.createSingleComp(empRqDto005);

        // method end log
        log.info("createCompTempData Method Return Data : " + empRsDto005);
        log.info("createCompTempData Method End !!!");
    }

    // 2. 부서 정보 등록 메소드
    public void createDeptTempData() throws Exception {
        // method start log
        log.info("createDeptTempData Method Start !!!");

        EmpRqDto000 empRqDto000 = new EmpRqDto000();
        EmpRqDto000.Emp emp = new EmpRqDto000.Emp();
        emp.setEmpSeq(1);

        EmpRqDto000.Dept dept = new EmpRqDto000.Dept();
        dept.setDeptName("테스트 init 부서");
        dept.setParentDeptSeq(0);

        EmpRqDto000.Comp comp = new EmpRqDto000.Comp();
        comp.setCompSeq(1);

        empRqDto000.setEmp(emp);
        empRqDto000.setComp(comp);
        empRqDto000.setDept(dept);

        EmpRsDto000 singleDept = empService000.createSingleDept(empRqDto000);

        // method end log
        log.info("createDeptTempData Method Return Data : " + singleDept);
        log.info("createDeptTempData Method End !!!");
    }

    // 3. 직급 직책 직위 정보 등록 메소드
    public void createPositionTempData() throws Exception {
    }

    // 4. 사원 정보 등록 메소드
    public void createEmpTempData() throws Exception {
    }

    // 5. 캘린더 정보 등록 메소드
    public void createCalendarTempData() throws Exception {
    }

    // 6. 일정 정보 등록 메소드
    public void createSchTempData() throws Exception {
    }
}
