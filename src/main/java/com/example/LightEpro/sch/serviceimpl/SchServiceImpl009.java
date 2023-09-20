package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch009.SchRqDto009;
import com.example.LightEpro.sch.dto.sch009.SchRsDto009;
import com.example.LightEpro.sch.mapper.SchMapper009;
import com.example.LightEpro.sch.service.SchService009;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl009 implements SchService009 {

    // schMapper009 선언
    private final SchMapper009 schMapper009;

    // 나의 캘린더 목록 조회 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto009 findMyCalendarListInfo(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findMyCalendarListInfo Method Start !!!");
        log.info("findMyCalendarListInfo Method Request Data : " + schRqDto009);

        // 나의 캘린더 목록 조회 메소드 호출
        List<SchRsDto009.Calendar> myCalendarList = findMyCalendarList(schRqDto009);
        // 나의 캘린더 목록 카운팅 메소드 호출
        int myCalendarListCnt = myCalendarList == null ? 0 : myCalendarList.size();

        // schRsDto009 객체 builder 패턴을 통해 객체 생성
        SchRsDto009 schRsDto009 = SchRsDto009.builder()
                .myCalendarList(myCalendarList)
                .myCalendarListCnt(myCalendarListCnt)
                .build();

        // method end log
        log.info("findMyCalendarListInfo Method Return Data : " + schRsDto009);
        log.info("findMyCalendarListInfo Method End !!!");

        // return
        return schRsDto009;
    }

    // 나의 캘린더 목록 조회 메소드
    @Override
    public List<SchRsDto009.Calendar> findMyCalendarList(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findMyCalendarList Method Start !!!");
        log.info("findMyCalendarList Method Request Data : " + schRqDto009);

        // 캘린더 목록 조회 Mapper 호출
        List<SchRsDto009.Calendar> myCalendarList = schMapper009.selectMyCalendarList(schRqDto009);

        // method end log
        log.info("findMyCalendarListInfo Method Return Data : " + myCalendarList);
        log.info("findMyCalendarListInfo Method End !!!");

        // return
        return myCalendarList;
    }
}
