package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch006.SchRqDto006;
import com.example.LightEpro.sch.dto.sch006.SchRsDto006;
import com.example.LightEpro.sch.mapper.SchMapper005;
import com.example.LightEpro.sch.mapper.SchMapper006;
import com.example.LightEpro.sch.service.SchService006;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl006 implements SchService006 {
    // schMapper006 선언
    private final SchMapper006 schMapper006;

    // 단일 캘린더 정보 조회 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto006 findCalendarInfo(SchRqDto006 schRqDto006) throws Exception {
        // method start log
        log.info("findCalendarInfo Method Start !!!");
        log.info("findCalendarInfo Method Request Data : " + schRqDto006);
        
        // 단일 캘린더 조회 메소드 호출
        SchRsDto006.Calendar calendar = findCalendar(schRqDto006);
        // 단일 캘린더 소유자 조회 메소드 호출
        SchRsDto006.Owner owner = findOwner(schRqDto006);
        // 단일 캘린더 관리자 조회 메소드 호출
        List<SchRsDto006.Manager> managers = findManagers(schRqDto006);

        // schRsDto006 객체 builder 패턴을 통해 객체 생성
        SchRsDto006 schRsDto006 = SchRsDto006.builder()
                .calendar(calendar)
                .owner(owner)
                .managers(managers)
                .build();

        // method end log
        log.info("findCalendarInfo Method Return Data : " + schRsDto006);
        log.info("findCalendarInfo Method End !!!");

        // return
        return schRsDto006;
    }

    // 캘린더 조회 메소드
    @Override
    public SchRsDto006.Calendar findCalendar(SchRqDto006 schRqDto006) throws Exception {
        // method start log
        log.info("findCalendar Method Start !!!");
        log.info("findCalendar Method Request Data : " + schRqDto006);

        // 캘린더 조회 Mapper 호출
        SchRsDto006.Calendar calendar = schMapper006.selectCalendar(schRqDto006);

        // method end log
        log.info("findCalendar Method Return Data : " + calendar);
        log.info("findCalendar Method End !!!");

        // return
        return calendar;
    }

    // 캘린더 소유자 조회 메소드
    @Override
    public SchRsDto006.Owner findOwner(SchRqDto006 schRqDto006) throws Exception {
        // method start log
        log.info("findOwner Method Start !!!");
        log.info("findOwner Method Request Data : " + schRqDto006);

        // 캘린더 소유자 조회 Mapper 호출
        SchRsDto006.Owner owner = schMapper006.selectOwner(schRqDto006);

        // method end log
        log.info("findOwner Method Return Data : " + owner);
        log.info("findOwner Method End !!!");

        // return
        return owner;
    }

    // 캘린더 관리자 조회 메소드
    @Override
    public List<SchRsDto006.Manager> findManagers(SchRqDto006 schRqDto006) throws Exception {
        // method start log
        log.info("findManagers Method Start !!!");
        log.info("findManagers Method Request Data : " + schRqDto006);

        // 캘린더 관리자 조회 Mapper 호출
        List<SchRsDto006.Manager> managers = schMapper006.selectManagers(schRqDto006);

        // method end log
        log.info("findManagers Method Return Data : " + managers);
        log.info("findManagers Method End !!!");

        // return
        return managers;
    }
}
