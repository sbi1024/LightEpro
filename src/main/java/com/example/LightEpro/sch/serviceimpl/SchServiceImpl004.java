package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;
import com.example.LightEpro.sch.mapper.SchMapper004;
import com.example.LightEpro.sch.service.SchService004;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl004 implements SchService004 {
    // schMapper004 선언
    private final SchMapper004 schMapper004;

    // 월 기준 일정 목록 조회 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto004 findSchedulesInfo(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("findSchedulesInfo Method Start !!!");
        log.info("findSchedulesInfo Method Request Data : " + schRqDto004);

        // 일정 목록 조회 메소드 호출
        List<SchRsDto004.Schedule> schedules = findSchedules(schRqDto004);
        // 일정 목록 카운팅 메소드 호출
        int schedulesCnt = schedules == null ? 0 : schedules.size();
        // schRsDto004 객체 builder 패턴을 통해 객체 생성
        SchRsDto004 schRsDto004 = SchRsDto004.builder()
                .schedules(schedules)
                .schedulesCnt(schedulesCnt)
                .build();

        // method end log
        log.info("findSchedulesInfo Method Return Data : " + schRqDto004);
        log.info("findSchedulesInfo Method End !!!");

        // return
        return schRsDto004;
    }

    // 일정 목록 조회 메소드
    @Override
    public List<SchRsDto004.Schedule> findSchedules(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("findSchedules Method Start !!!");
        log.info("findSchedules Method Request Data : " + schRqDto004);

        // 권한 있는 캘린더 목록 리스트/일자 값을 통해 일정 목록 조회 메소드 호출
        List<SchRsDto004.Schedule> schedulesByAuthorizedCalendarSequencesAndDate = confirmSchedulesByAuthorizedCalendarSequencesAndDate(schRqDto004);
        // 권한 없는 캘린더 목록 리스트/일자 값을 통해 일정 목록 조회 메소드
        List<SchRsDto004.Schedule> schedulesByUnAuthorizedCalendarSequencesAndDate = confirmSchedulesByUnAuthorizedCalendarSequencesAndDate(schRqDto004);
        // 일정 목록 = 권한 있는 캘린더에서의 일정 목록 + 권한 없는 캘린더에서의 일정 목록
        List<SchRsDto004.Schedule> schedules = Stream.of(schedulesByAuthorizedCalendarSequencesAndDate, schedulesByUnAuthorizedCalendarSequencesAndDate)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // method end log
        log.info("findSchedules Method Return Data : " + schedules);
        log.info("findSchedules Method End !!!");

        // return
        return schedules;
    }

    // 권한 있는 캘린더 시퀀스/일자 값을 통해 일정 목록 검증 메소드
    @Override
    public List<SchRsDto004.Schedule> confirmSchedulesByAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("confirmSchedulesByAuthorizedCalendarSequencesAndDate Method Start !!!");
        log.info("confirmSchedulesByAuthorizedCalendarSequencesAndDate Method Request Data : " + schRqDto004);

        // 요청값으로 받은 권한 있는 캘린더 시퀀스/일자 값을 통해 일정 목록 조회 결과값을 담기 위한 변수 선언
        List<SchRsDto004.Schedule> schedulesByAuthorizedCalendarSequencesAndDate;

        // 요청값으로 받은 권한 있는 캘린더 시퀀스 값 추출
        List<Integer> authorizedCalendarSequences = schRqDto004.getCalendar().getAuthorizedCalendarSequences();
        // authorizedCalendarSequences null 및 size 검증
        if (authorizedCalendarSequences == null || authorizedCalendarSequences.size() == 0) {
            log.info("confirmSchedulesByAuthorizedCalendarSequencesAndDate Method authorizedCalendarSequences == null or authorizedCalendarSequences.size() == 0");
            return new ArrayList<>();
        }

        // 권한 있는 캘린더 시퀀스/일자 값을 통해 일정 목록 조회 메소드 호출
        schedulesByAuthorizedCalendarSequencesAndDate = findSchedulesByAuthorizedCalendarSequencesAndDate(schRqDto004);

        // method end log
        log.info("confirmSchedulesByAuthorizedCalendarSequencesAndDate Method Return Data : " + schedulesByAuthorizedCalendarSequencesAndDate);
        log.info("confirmSchedulesByAuthorizedCalendarSequencesAndDate Method End !!!");

        // return
        return schedulesByAuthorizedCalendarSequencesAndDate;
    }

    // 권한 있는 캘린더 시퀀스/일자 값을 통해 일정 목록 조회 메소드
    @Override
    public List<SchRsDto004.Schedule> findSchedulesByAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("findSchedulesByAuthorizedCalendarSequencesAndDate Method Start !!!");
        log.info("findSchedulesByAuthorizedCalendarSequencesAndDate Method Request Data : " + schRqDto004);

        // 권한 있는 캘린더 목록 리스트/일자 값을 통해 일정 목록 조회 Mapper 호출
        List<SchRsDto004.Schedule> schedulesByAuthorizedCalendarSequencesAndDate = schMapper004.selectSchedulesByAuthorizedCalendarSequencesAndDate(schRqDto004);
        // schedulesByAuthorizedCalendarSequencesAndDate null 및 size 검증
        if (schedulesByAuthorizedCalendarSequencesAndDate == null || schedulesByAuthorizedCalendarSequencesAndDate.size() == 0) {
            log.info("findSchedulesByAuthorizedCalendarSequencesAndDate Method schedulesByAuthorizedCalendarSequencesAndDate == null or schedulesByAuthorizedCalendarSequencesAndDate.size() == 0");
            return new ArrayList<>();
        }

        // method end log
        log.info("findSchedulesByAuthorizedCalendarSequencesAndDate Method Return Data : " + schedulesByAuthorizedCalendarSequencesAndDate);
        log.info("findSchedulesByAuthorizedCalendarSequencesAndDate Method End !!!");

        // return
        return schedulesByAuthorizedCalendarSequencesAndDate;
    }

    // 권한 없는 캘린더 시퀀스/일자 값을 통해 일정 목록 검증 메소드
    @Override
    public List<SchRsDto004.Schedule> confirmSchedulesByUnAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("confirmSchedulesByUnAuthorizedCalendarSequencesAndDate Method Start !!!");
        log.info("confirmSchedulesByUnAuthorizedCalendarSequencesAndDate Method Request Data : " + schRqDto004);

        // 요청값으로 받은 권한 있는 캘린더 시퀀스/일자 값을 통해 일정 목록 조회 결과값을 담기 위한 변수 선언
        List<SchRsDto004.Schedule> schedulesByUnAuthorizedCalendarSequencesAndDate;

        // 요청값으로 받은 권한 없는 캘린더 시퀀스 값 추출
        List<Integer> unAuthorizedCalendarSequences = schRqDto004.getCalendar().getUnAuthorizedCalendarSequences();
        // unAuthorizedCalendarSequences null 및 size 검증
        if (unAuthorizedCalendarSequences == null || unAuthorizedCalendarSequences.size() == 0) {
            log.info("confirmSchedulesByUnAuthorizedCalendarSequencesAndDate Method unAuthorizedCalendarSequences == null or unAuthorizedCalendarSequences.size() == 0");
            return new ArrayList<>();
        }

        // 권한 있는 캘린더 시퀀스/일자 값을 통해 일정 목록 조회 메소드 호출
        schedulesByUnAuthorizedCalendarSequencesAndDate = findSchedulesByUnAuthorizedCalendarSequencesAndDate(schRqDto004);

        // method end log
        log.info("confirmSchedulesByUnAuthorizedCalendarSequencesAndDate Method Return Data : " + schedulesByUnAuthorizedCalendarSequencesAndDate);
        log.info("confirmSchedulesByUnAuthorizedCalendarSequencesAndDate Method End !!!");

        // return
        return schedulesByUnAuthorizedCalendarSequencesAndDate;
    }

    // 권한 없는 캘린더 목록 리스트/일자 값을 통해 일정 목록 조회 메소드
    @Override
    public List<SchRsDto004.Schedule> findSchedulesByUnAuthorizedCalendarSequencesAndDate(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("findSchedulesByUnAuthorizedCalendarSequencesAndDate Method Start !!!");
        log.info("findSchedulesByUnAuthorizedCalendarSequencesAndDate Method Request Data : " + schRqDto004);

        // 권한 없는 캘린더 목록 리스트/일자 값을 통해 일정 목록 조회 Mapper 호출
        List<SchRsDto004.Schedule> schedulesByUnAuthorizedCalendarSequencesAndDate = schMapper004.selectSchedulesByUnAuthorizedCalendarSequencesAndDate(schRqDto004);
        // schedulesByUnAuthorizedCalendarSequencesAndDate null 및 size 검증
        if (schedulesByUnAuthorizedCalendarSequencesAndDate == null || schedulesByUnAuthorizedCalendarSequencesAndDate.size() == 0) {
            log.info("findSchedulesByUnAuthorizedCalendarSequencesAndDate Method schedulesByUnAuthorizedCalendarSequencesAndDate == null or schedulesByUnAuthorizedCalendarSequencesAndDate.size() == 0");
            return new ArrayList<>();
        }

        // method end log
        log.info("findSchedulesByUnAuthorizedCalendarSequencesAndDate Method Return Data : " + schedulesByUnAuthorizedCalendarSequencesAndDate);
        log.info("findSchedulesByUnAuthorizedCalendarSequencesAndDate Method End !!!");

        // return
        return schedulesByUnAuthorizedCalendarSequencesAndDate;
    }
}
