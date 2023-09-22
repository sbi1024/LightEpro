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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        // 권한 있는 캘린더 목록 리스트 조회 메소드 호출
        List<SchRsDto009.Calendar> authorizedCalendarList = confirmAuthorizedCalendarList(schRqDto009);
        // 권한 없는 캘린더 목록 리스트 조회 메소드 호출
        List<SchRsDto009.Calendar> unAuthorizedCalendarList = confirmUnAuthorizedCalendarList(schRqDto009);
        // 나의 캘린더 리스트 = 권한 있는 캘린더 목록 리스트 + 권한 없는 캘린더 목록 리스트
        List<SchRsDto009.Calendar> myCalendarList = Stream.of(authorizedCalendarList, unAuthorizedCalendarList)
                .flatMap(calendarList -> calendarList.stream())
                .collect(Collectors.toList());

        // method end log
        log.info("findMyCalendarList Method Return Data : " + myCalendarList);
        log.info("findMyCalendarList Method End !!!");

        // return
        return myCalendarList;
    }

    // 권한 있는 캘린더 목록 조회 및 schRqDto009 객체 데이터 재 할당 메소드
    @Override
    public List<SchRsDto009.Calendar> confirmAuthorizedCalendarList(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("confirmAuthorizedCalendarList Method Start !!!");
        log.info("confirmAuthorizedCalendarList Method Request Data : " + schRqDto009);

        // authorizedCalendarList null 검증
        List<SchRsDto009.Calendar> authorizedCalendarList = findAuthorizedCalendarList(schRqDto009);
        if (authorizedCalendarList == null) {
            log.info("confirmAuthorizedCalendarList Method authorizedCalendarList == null");
            return new ArrayList<>();
        }

        // authorizedCalendarList 캘린더 시퀀스 값 추출 및 schRqDto009 객체에 할당
        schRqDto009.setAuthorizedCalendarList(authorizedCalendarList.stream()
                .map(authorizedCalendar -> authorizedCalendar.getCalSeq())
                .collect(Collectors.toList()));

        // method end log
        log.info("confirmAuthorizedCalendarList Method Return Data : " + authorizedCalendarList);
        log.info("confirmAuthorizedCalendarList Method End !!!");

        // return
        return authorizedCalendarList;
    }

    // 권한 없는 캘린더 목록 조회 및 schRqDto009 객체 데이터 재 할당 메소드
    @Override
    public List<SchRsDto009.Calendar> confirmUnAuthorizedCalendarList(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("confirmUnAuthorizedCalendarList Method Start !!!");
        log.info("confirmUnAuthorizedCalendarList Method Request Data : " + schRqDto009);

        List<SchRsDto009.Calendar> unAuthorizedCalendarList = findUnAuthorizedCalendarList(schRqDto009);
        if (unAuthorizedCalendarList == null) {
            log.info("confirmUnAuthorizedCalendarList Method unAuthorizedCalendarList == null");
            return new ArrayList<>();
        }
        schRqDto009.setAuthorizedCalendarList(unAuthorizedCalendarList.stream()
                .map(unAuthorizedCalendar -> unAuthorizedCalendar.getCalSeq())
                .collect(Collectors.toList()));

        // method end log
        log.info("confirmUnAuthorizedCalendarList Method Return Data : " + unAuthorizedCalendarList);
        log.info("confirmUnAuthorizedCalendarList Method End !!!");

        // return
        return unAuthorizedCalendarList;
    }

    // 권한 있는 캘린더 목록 리스트 조회 메소드
    @Override
    public List<SchRsDto009.Calendar> findAuthorizedCalendarList(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findAuthorizedCalendarList Method Start !!!");
        log.info("findAuthorizedCalendarList Method Request Data : " + schRqDto009);

        // 권한 있는 캘린더 목록 조회 Mapper 호출
        List<SchRsDto009.Calendar> authorizedCalendarList = schMapper009.selectAuthorizedCalendarList(schRqDto009);

        // method end log
        log.info("findAuthorizedCalendarList Method Return Data : " + authorizedCalendarList);
        log.info("findAuthorizedCalendarList Method End !!!");

        // return
        return authorizedCalendarList;
    }

    // 권한 없는 캘린더 목록 리스트 조회 메소드
    @Override
    public List<SchRsDto009.Calendar> findUnAuthorizedCalendarList(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findAuthorizedCalendarList Method Start !!!");
        log.info("findAuthorizedCalendarList Method Request Data : " + schRqDto009);

        // 권한 없는 캘린더 목록 조회 Mapper 호출
        List<SchRsDto009.Calendar> unAuthorizedCalendarList = schMapper009.selectUnAuthorizedCalendarList(schRqDto009);

        // method end log
        log.info("findAuthorizedCalendarList Method Return Data : " + unAuthorizedCalendarList);
        log.info("findAuthorizedCalendarList Method End !!!");

        // return
        return unAuthorizedCalendarList;
    }
}
