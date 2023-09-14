package com.example.LightEpro.init;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.dto.emp005.EmpRqDto005;
import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import com.example.LightEpro.emp.dto.emp015.EmpRqDto015;
import com.example.LightEpro.emp.service.EmpService000;
import com.example.LightEpro.emp.service.EmpService005;
import com.example.LightEpro.emp.service.EmpService010;
import com.example.LightEpro.emp.service.EmpService015;
import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.service.SchService000;
import com.example.LightEpro.sch.service.SchService005;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationInit implements InitializingBean {

    // 회사
    private final EmpService005 empService005;
    // 부서
    private final EmpService000 empService000;
    // 직책/직위/직급
    private final EmpService010 empService010;
    // 사원
    private final EmpService015 empService015;
    // 캘린더
    private final SchService005 schService005;
    // 일정
    private final SchService000 schService000;


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

        for (int i = 1; i <= 5; i++) {
            // empRqDto005 객체 생성
            EmpRqDto005 empRqDto005 = new EmpRqDto005();
            EmpRqDto005.User user = new EmpRqDto005.User();
            EmpRqDto005.Comp comp = new EmpRqDto005.Comp();

            // user 객체 데이터 할당
            user.setUserCompSeq(1);
            user.setUserDeptSeq(1);
            user.setUserSeq(1);

            // comp 객체 데이터 할당
            comp.setCompName("테스트 init 회사" + i);
            comp.setParentCompSeq(i - 1);

            // empRqDto005 객체 데이터 할당
            empRqDto005.setUser(user);
            empRqDto005.setComp(comp);

            // 회사 등록
            empService005.createSingleComp(empRqDto005);
        }

        // method end log
        log.info("createCompTempData Method End !!!");
    }

    // 2. 부서 정보 등록 메소드
    public void createDeptTempData() throws Exception {
        // method start log
        log.info("createDeptTempData Method Start !!!");

        for (int i = 1; i <= 5; i++) {
            // empRqDto000 객체 생성
            EmpRqDto000 empRqDto000 = new EmpRqDto000();
            EmpRqDto000.User user = new EmpRqDto000.User();
            EmpRqDto000.Dept dept = new EmpRqDto000.Dept();
            EmpRqDto000.Comp comp = new EmpRqDto000.Comp();

            // user 객체 데이터 할당
            user.setUserCompSeq(1);
            user.setUserDeptSeq(1);
            user.setUserSeq(1);

            // dept 객체 데이터 할당
            dept.setDeptName("테스트 init 부서" + i);
            dept.setParentDeptSeq(i - 1);

            // comp 객체 데이터 할당
            comp.setCompSeq(i);

            // schRqDto015 객체 데이터 할당
            empRqDto000.setUser(user);
            empRqDto000.setComp(comp);
            empRqDto000.setDept(dept);

            // 부서 등록
            empService000.createSingleDept(empRqDto000);
        }

        // method end log
        log.info("createDeptTempData Method End !!!");
    }

    // 3. 직급 직책 직위 정보 등록 메소드
    public void createPositionTempData() throws Exception {
        // method start log
        log.info("createPositionTempData Method Start !!!");

        for (int i = 1; i <= 3; i++) {
            // empRqDto010 객체 생성
            EmpRqDto010 empRqDto010 = new EmpRqDto010();
            EmpRqDto010.User user = new EmpRqDto010.User();
            EmpRqDto010.Comp comp = new EmpRqDto010.Comp();
            EmpRqDto010.Position position = new EmpRqDto010.Position();

            // user 객체 데이터 할당
            user.setUserCompSeq(1);
            user.setUserDeptSeq(1);
            user.setUserSeq(1);

            // comp 객체 데이터 할당
            comp.setCompSeq(1);

            // position 객체 데이터 할당
            position.setPositionCodeType(i * 10);
            if (i == 1) {
                position.setPositionCodeInfo("팀원");
            } else if (i == 2) {
                position.setPositionCodeInfo("사원");
            } else if (i == 3) {
                position.setPositionCodeInfo("사원 1호봉");
            }

            // empRqDto015 객체 데이터 할당
            empRqDto010.setUser(user);
            empRqDto010.setComp(comp);
            empRqDto010.setPosition(position);

            // 직급 직책 직위 등록
            empService010.createSinglePosition(empRqDto010);
        }

        // method end log
        log.info("createPositionTempData Method End !!!");
    }

    // 4. 사원 정보 등록 메소드
    public void createEmpTempData() throws Exception {
        // method start log
        log.info("createEmpTempData Method Start !!!");

        for (int i = 1; i <= 5; i++) {
            // empRqDto015 객체 생성
            EmpRqDto015 empRqDto015 = new EmpRqDto015();
            EmpRqDto015.User user = new EmpRqDto015.User();
            EmpRqDto015.Emp emp = new EmpRqDto015.Emp();
            EmpRqDto015.EmpAccount empAccount = new EmpRqDto015.EmpAccount();
            EmpRqDto015.EmpMapping empMapping = new EmpRqDto015.EmpMapping();

            // user 객체 데이터 할당
            user.setUserCompSeq(1);
            user.setUserDeptSeq(1);
            user.setUserSeq(1);

            // emp 객체 데이터 할당
            emp.setEmpName("테스트 init 사원 이름" + i);
            emp.setBirthDate(LocalDate.now());
            emp.setHireDate(LocalDate.now());

            // empAccount 객체 데이터 할당
            empAccount.setUserId("테스트 init 아이디" + i);
            empAccount.setUserPw("테스트 init 비밀번호" + i);

            // empMapping 객체 데이터 할당
            empMapping.setCompSeq(1);
            empMapping.setDeptSeq(1);
            empMapping.setMainCompYn("Y");
            empMapping.setMainDeptYn("Y");
            empMapping.setPositionSeq(1); // 직책 10
            empMapping.setPositionSpotSeq(2); // 직위 20
            empMapping.setPositionGradeSeq(3); // 직급 30

            // empRqDto015 객체 데이터 할당
            empRqDto015.setUser(user);
            empRqDto015.setEmp(emp);
            empRqDto015.setEmpMapping(empMapping);
            empRqDto015.setEmpAccount(empAccount);

            // 사원 등록
            empService015.createSingleEmp(empRqDto015);
        }

        // method end log
        log.info("createEmpTempData Method End !!!");
    }

    // 5. 캘린더 정보 등록 메소드
    public void createCalendarTempData() throws Exception {
        // method start log
        log.info("createCalendarTempData Method Start !!!");

        for (int i = 1; i <= 4; i++) {
            // schRqDto005 객체 생성
            SchRqDto005 schRqDto005 = new SchRqDto005();
            SchRqDto005.User user = new SchRqDto005.User();
            SchRqDto005.Calendar calendar = new SchRqDto005.Calendar();
            SchRqDto005.Owner owner = new SchRqDto005.Owner();
            List<SchRqDto005.Manager> managers = new ArrayList<>();
            SchRqDto005.Manager manager = new SchRqDto005.Manager();

            // user 객체 데이터 할당
            user.setUserCompSeq(1);
            user.setUserDeptSeq(1);
            user.setUserSeq(1);

            if (i % 2 != 0) {
                // calendar 객체 데이터 할당
                calendar.setCalTitle("테스트 init 공유 캘린더" + i);
                calendar.setCalType("M");

                // owner 객체 데이터 할당
                owner.setCdeSeq(i);
                owner.setCdeType("E");

                // manager 객체 데이터 할당
                manager.setCdeSeq(i + 1);
                manager.setCdeType("D");
                managers.add(manager);
            } else {
                // calendar 객체 데이터 할당
                calendar.setCalTitle("테스트 init 개인 캘린더" + i);
                calendar.setCalType("E");

                // owner 객체 데이터 할당
                owner.setCdeSeq(i);
                owner.setCdeType("E");
            }

            // schRqDto005 객체 데이터 할당
            schRqDto005.setUser(user);
            schRqDto005.setCalendar(calendar);
            schRqDto005.setOwner(owner);
            schRqDto005.setManagers(managers);

            // 캘린더 등록
            schService005.createCalendarInfo(schRqDto005);
        }

        // method end log
        log.info("createCalendarTempData Method End !!!");
    }

    // 6. 일정 정보 등록 메소드
    public void createSchTempData() throws Exception {
        // method start log
        log.info("createCalendarTempData Method Start !!!");

        for (int i = 1; i <= 5; i++) {
            // schRqDto000 객체 생성
            SchRqDto000 schRqDto000 = new SchRqDto000();
            SchRqDto000.User user = new SchRqDto000.User();
            SchRqDto000.Schedule schedule = new SchRqDto000.Schedule();
            SchRqDto000.Calendar calendar = new SchRqDto000.Calendar();
            List<SchRqDto000.Participant> participants = new ArrayList<>();
            SchRqDto000.Participant participant = new SchRqDto000.Participant();
            List<SchRqDto000.DisclosureScope> disclosureScopes = new ArrayList<>();
            SchRqDto000.DisclosureScope disclosureScope = new SchRqDto000.DisclosureScope();

            // user 객체 데이터 할당
            user.setUserCompSeq(1);
            user.setUserDeptSeq(1);
            user.setUserSeq(i);

            // schedule 객체 데이터 할당
            schedule.setStartDate(LocalDateTime.now());
            schedule.setEndDate(LocalDateTime.now());
            schedule.setAlldayYn("N");
            schedule.setSchTitle("테스트 init 제목" + i);

            // calendar 객체 데이터 할당
            calendar.setCalSeq(i);

            if (i % 2 != 0) {
                // participant 객체 데이터 할당
                participant.setCdeSeq(i);
                participant.setCdeType("E");
                participants.add(participant);

                // disclosureScope 객체 데이터 할당
                disclosureScope.setCdeSeq(i);
                if (i % 3 == 0) {
                    disclosureScope.setCdeType("C");
                } else {
                    disclosureScope.setCdeType("D");
                }
                disclosureScopes.add(disclosureScope);
            } else {
                // participant 객체 데이터 할당
                participant.setCdeSeq(i);
                participant.setCdeType("E");
                participants.add(participant);
            }

            // schRqDto000 객체 데이터 할당
            schRqDto000.setUser(user);
            schRqDto000.setSchedule(schedule);
            schRqDto000.setCalendar(calendar);
            schRqDto000.setParticipants(participants);
            schRqDto000.setDisclosureScopes(disclosureScopes);

            // 일정 등록
            schService000.createScheduleInfo(schRqDto000);
        }

        // method end log
        log.info("createCalendarTempData Method End !!!");
    }
}
