package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import com.example.LightEpro.sch.dto.sch008.SchRsDto008;
import com.example.LightEpro.sch.mapper.SchMapper008;
import com.example.LightEpro.sch.service.SchService008;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl008 implements SchService008 {
    // schMapper008 선언
    private final SchMapper008 schMapper008;

    // 단일 캘린더 정보 삭제 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto008 removeCalendarInfo(SchRqDto008 schRqDto008) throws Exception {
        // method start log
        log.info("removeSingleCal Method Start !!!");
        log.info("removeSingleCal Method Request Data : " + schRqDto008);

        // 캘린더 삭제 메소드 호출
        int removeCalendarCnt = removeCalendar(schRqDto008);
        // 캘린더 구성원 삭제 메소드 호출
        int removeCalendarUsersCnt = removeCalendarUsers(schRqDto008);
        // 캘린더에 포함된 일정 삭제 메소드 호출
        int removeSchedulesCnt = removeSchedules(schRqDto008);
        // 캘린더에 포함된 일정 구성원 삭제 메소드 호출
        int removeScheduleUsersCnt = removeScheduleUsers(schRqDto008);

        // schRsDto008 객체 builder 패턴을 통해 객체 생성
        SchRsDto008 schRsDto008 = SchRsDto008.builder()
                .calSeq(schRqDto008.getCalendar().getCalSeq())
                .removeCalendarCnt(removeCalendarCnt)
                .removeCalendarUsersCnt(removeCalendarUsersCnt)
                .removeSchedulesCnt(removeSchedulesCnt)
                .removeScheduleUsersCnt(removeScheduleUsersCnt)
                .build();

        // method end log
        log.info("removeSingleCal Method Return Data : " + schRsDto008);
        log.info("removeSingleCal Method End !!!");

        // return
        return schRsDto008;
    }

    // 캘린더 삭제 메소드
    @Override
    public int removeCalendar(SchRqDto008 schRqDto008) throws Exception {
        // method start log
        log.info("removeCalendar Method Start !!!");
        log.info("removeCalendar Method Request Data : " + schRqDto008);

        // 캘린더 삭제 Mapper 호출
        int updateCalendarCnt = schMapper008.updateCalendar(schRqDto008);

        // method end log
        log.info("removeCalendar Method Start !!!");
        log.info("removeCalendar Method Request Data : " + updateCalendarCnt);

        // return
        return updateCalendarCnt;
    }

    // 캘린더 구성원 삭제 메소드
    @Override
    public int removeCalendarUsers(SchRqDto008 schRqDto008) throws Exception {
        // method start log
        log.info("removeCalUsers Method Start !!!");
        log.info("removeCalUsers Method Request Data : " + schRqDto008);

        // 캘린더 구성원 삭제 Mapper 호출
        int updateCalendarUsersCnt = schMapper008.updateCalendarUsers(schRqDto008);

        // method end log
        log.info("removeCalUsers Method Start !!!");
        log.info("removeCalUsers Method Request Data : " + updateCalendarUsersCnt);

        // return
        return updateCalendarUsersCnt;
    }

    // 일정 삭제 메소드
    @Override
    public int removeSchedules(SchRqDto008 schRqDto008) throws Exception {
        return 0;
    }

    // 일정 구성원 삭제 메소드
    @Override
    public int removeScheduleUsers(SchRqDto008 schRqDto008) throws Exception {
        return 0;
    }
}
