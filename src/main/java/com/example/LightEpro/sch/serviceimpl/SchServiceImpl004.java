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
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl004 implements SchService004 {
    // schMapper004 선언
    private final SchMapper004 schMapper004;

    // 월 기준 일정 목록 조회 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto004 findScheduleListInfo(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("findScheduleListInfo Method Start !!!");
        log.info("findScheduleListInfo Method Request Data : " + schRqDto004);

        // 일정 목록 조회 메소드 호출
        List<SchRsDto004.Schedule> scheduleList = findScheduleList(schRqDto004);
        // 일정 목록 카운팅 메소드 호출
        int scheduleListCnt = scheduleList == null ? 0 : scheduleList.size();

        // schRsDto004 객체 builder 패턴을 통해 객체 생성
        SchRsDto004 schRsDto004 = SchRsDto004.builder()
                .scheduleList(scheduleList)
                .scheduleListCnt(scheduleListCnt)
                .build();

        // method end log
        log.info("findScheduleListInfo Method Return Data : " + schRqDto004);
        log.info("findScheduleListInfo Method End !!!");

        // return
        return schRsDto004;
    }

    // 일정 목록 조회 메소드
    @Override
    public List<SchRsDto004.Schedule> findScheduleList(SchRqDto004 schRqDto004) throws Exception {
        // method start log
        log.info("findScheduleList Method Start !!!");
        log.info("findScheduleList Method Request Data : " + schRqDto004);

        // 일정 목록 조회 Mapper 호출
        List<SchRsDto004.Schedule> scheduleList = schMapper004.selectScheduleList(schRqDto004);

        // method end log
        log.info("findScheduleList Method Return Data : " + scheduleList);
        log.info("findScheduleList Method End !!!");

        // return
        return scheduleList;
    }
}
