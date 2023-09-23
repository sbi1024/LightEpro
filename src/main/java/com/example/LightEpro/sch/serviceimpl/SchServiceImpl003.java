package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import com.example.LightEpro.sch.dto.sch003.SchRsDto003;
import com.example.LightEpro.sch.mapper.SchMapper003;
import com.example.LightEpro.sch.service.SchService003;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl003 implements SchService003 {
    // schMapper003 선언
    private final SchMapper003 schMapper003;

    // 단일 일정 정보 삭제 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto003 removeScheduleInfo(SchRqDto003 schRqDto003) throws Exception {
        // method start log
        log.info("removeScheduleInfo Method Start !!!");
        log.info("removeScheduleInfo Method Request Data : " + schRqDto003);

        // 일정 및 구성원 삭제 메소드 호출
        int removeScheduleAndUsersCnt = removeScheduleAndUsers(schRqDto003);

        // schRsDto003 객체 builder 패턴을 통해 객체 생성
        SchRsDto003 schRsDto003 = SchRsDto003.builder()
                .schmSeq(schRqDto003.getSchedule().getSchmSeq())
                .schSeq(schRqDto003.getSchedule().getSchSeq())
                .removeScheduleAndUsersCnt(removeScheduleAndUsersCnt)
                .build();

        // method end log
        log.info("removeScheduleInfo Method Return Data : " + schRsDto003);
        log.info("removeScheduleInfo Method End !!!");

        // return
        return schRsDto003;
    }

    // 일정 및 구성원 삭제 메소드
    @Override
    public int removeScheduleAndUsers(SchRqDto003 schRqDto003) throws Exception {
        // method start log
        log.info("removeSchedule Method Start !!!");
        log.info("removeSchedule Method Request Data : " + schRqDto003);

        // 일정 삭제 Mapper 호출
        int updateScheduleCnt = schMapper003.updateScheduleAndUsers(schRqDto003);

        // method end log
        log.info("removeSchedule Method Return Data : " + updateScheduleCnt);
        log.info("removeSchedule Method End !!!");

        // return
        return updateScheduleCnt;
    }
}
