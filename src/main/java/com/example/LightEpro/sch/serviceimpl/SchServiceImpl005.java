package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch005.SchRsDto005;
import com.example.LightEpro.sch.mapper.SchMapper005;
import com.example.LightEpro.sch.service.SchService005;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl005 implements SchService005 {
    private final SchMapper005 schMapper005;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto005 createCalendar(SchRqDto005 schRqDto005) throws Exception {
        log.info("createCalendar Method Start !!!");
        log.info("createCalendar Method Request Data : " + schRqDto005);

        // 캘린더 등록을 위한 캘린더 시퀀스 값 추출
        int curSeq = findCurrentCalValue();
        // 객체 데이터 재 주입 메소드 호출
        assignObject(curSeq, schRqDto005);

        // 캘린더 등록 메소드 호출
        int insertCalRow = insertSingleCalendar(schRqDto005);
        // 캘린더 소유자 등록 메소드 호출
        int insertCalOwnerRow = insertCalOwner(schRqDto005);
        // 캘린더 관리자 등록 메소드 호출
        int insertCalManagerRow = insertCalManager(schRqDto005);

        SchRsDto005 schRsDto005 = SchRsDto005.builder()
                .calSeq(curSeq)
                .insertCalRow(insertCalRow)
                .insertCalOwnerRow(insertCalOwnerRow)
                .insertCalManagerRow(insertCalManagerRow)
                .build();

        log.info("createCalendar Method Return Data : " + schRsDto005);
        log.info("createCalendar Method End !!!");
        return schRsDto005;
    }

    @Override
    public int findCurrentCalValue() throws Exception {
        log.info("findCurrentCalValue Method Start !!!");

        int currentCalValue = schMapper005.findCurrentCalValue();

        log.info("findCurrentCalValue Method Return Data : " + currentCalValue);
        log.info("findCurrentCalValue Method End !!!");
        return currentCalValue;
    }

    @Override
    public void assignObject(int curSeq, SchRqDto005 schRqDto005) throws Exception {
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + "curSeq : " + curSeq + "," + "schRqDto005 :" + schRqDto005);

        // schRqDto005 객체로 부터 내부 클래스 객체 생성
        SchRqDto005.Emp emp = schRqDto005.getEmp();
        SchRqDto005.Calender calender = schRqDto005.getCalender();
        SchRqDto005.Owner owner = schRqDto005.getOwner();
        List<SchRqDto005.Manager> managers = schRqDto005.getManagers();

        // 사용자 empSeq 값 추출
        int empSeq = emp.getEmpSeq();

        // 캘랜더 객체에 캘린더 시퀀스 , createSeq 할당
        calender.setCalSeq(curSeq);
        calender.setCreateSeq(empSeq);

        // 소유자 객채에 캘린더 시퀀스 , createSeq 할당
        owner.setCalSeq(curSeq);
        owner.setCreateSeq(empSeq);

        if (managers != null && managers.size() > 0) {
            // 반복문을 통해 관리자 객체에 calSeq , createSeq 할당
            for (SchRqDto005.Manager manager : managers) {
                manager.setCalSeq(curSeq);
                manager.setCreateSeq(empSeq);
            }
        }

        log.info("assignObject Method Result Data : " + schRqDto005);
        log.info("assignObject Method End !!!");
    }

    @Override
    public int insertSingleCalendar(SchRqDto005 schRqDto005) throws Exception {
        log.info("insertSingleCalendar Method Start !!!");
        log.info("insertSingleCalendar Method Request Data : " + schRqDto005);

        int insertCalRow = schMapper005.insertSingleCalendar(schRqDto005);

        log.info("insertSingleCalendar Method Return Data : " + insertCalRow);
        log.info("insertSingleCalendar Method End !!!");
        return insertCalRow;
    }

    @Override
    public int insertCalOwner(SchRqDto005 schRqDto005) throws Exception {
        log.info("insertCalOwner Method Start !!!");
        log.info("insertCalOwner Method Request Data : " + schRqDto005);

        int insertCalOwner = schMapper005.insertCalOwner(schRqDto005);

        log.info("insertCalOwner Method Return Data : " + insertCalOwner);
        log.info("insertCalOwner Method End !!!");
        return insertCalOwner;
    }

    @Override
    public int insertCalManager(SchRqDto005 schRqDto005) throws Exception {
        log.info("insertCalManager Method Start !!!");
        log.info("insertCalManager Method Request Data : " + schRqDto005);

        List<SchRqDto005.Manager> managers = schRqDto005.getManagers();
        if (managers == null || managers.size() == 0) {
            log.info("insertCalManager Method managers Data == null || managers.size() == 0");
            return 0;
        }
        int insertCalManager = schMapper005.insertCalManager(managers);

        log.info("insertCalManager Method Return Data : " + insertCalManager);
        log.info("insertCalManager Method End !!!");
        return insertCalManager;
    }
}
