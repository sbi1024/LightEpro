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
import java.util.Collection;
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
    public SchRsDto009 findMyCalendarsInfo(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findMyCalendarsInfo Method Start !!!");
        log.info("findMyCalendarsInfo Method Request Data : " + schRqDto009);

        // 나의 캘린더 목록 조회 메소드 호출
        List<SchRsDto009.Calendar> myCalendars = findMyCalendars(schRqDto009);
        // 나의 캘린더 목록 카운팅 메소드 호출
        int myCalendarsCnt = myCalendars == null ? 0 : myCalendars.size();

        // schRsDto009 객체 builder 패턴을 통해 객체 생성
        SchRsDto009 schRsDto009 = SchRsDto009.builder()
                .myCalendars(myCalendars)
                .myCalendarsCnt(myCalendarsCnt)
                .build();

        // method end log
        log.info("findMyCalendarsInfo Method Return Data : " + schRsDto009);
        log.info("findMyCalendarsInfo Method End !!!");

        // return
        return schRsDto009;
    }

    // 나의 캘린더 목록 조회 메소드
    @Override
    public List<SchRsDto009.Calendar> findMyCalendars(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findMyCalendars Method Start !!!");
        log.info("findMyCalendars Method Request Data : " + schRqDto009);

        // 권한 있는 캘린더 목록 리스트 조회 메소드 호출
        List<SchRsDto009.Calendar> authorizedCalendars = confirmAuthorizedCalendars(schRqDto009);
        // 권한 없는 캘린더 목록 리스트 조회 메소드 호출
        List<SchRsDto009.Calendar> unAuthorizedCalendars = confirmUnAuthorizedCalendars(schRqDto009);
        // 나의 캘린더 리스트 = 권한 있는 캘린더 목록 리스트 + 권한 없는 캘린더 목록 리스트
        List<SchRsDto009.Calendar> myCalendarList = Stream.of(authorizedCalendars, unAuthorizedCalendars)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // method end log
        log.info("findMyCalendars Method Return Data : " + myCalendarList);
        log.info("findMyCalendars Method End !!!");

        // return
        return myCalendarList;
    }

    // 권한 있는 캘린더 목록 조회 및 schRqDto009 객체 데이터 재 할당 메소드
    @Override
    public List<SchRsDto009.Calendar> confirmAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("confirmAuthorizedCalendars Method Start !!!");
        log.info("confirmAuthorizedCalendars Method Request Data : " + schRqDto009);

        // authorizedCalendarList null 검증
        List<SchRsDto009.Calendar> authorizedCalendars = findAuthorizedCalendars(schRqDto009);
        if (authorizedCalendars == null || authorizedCalendars.size() == 0) {
            log.info("confirmAuthorizedCalendars Method authorizedCalendars == null or authorizedCalendars.size() == 0");
            return new ArrayList<>();
        }

        // authorizedCalendarList 캘린더 시퀀스 값 추출 및 schRqDto009 객체에 할당
        schRqDto009.setAuthorizedCalendars(authorizedCalendars.stream()
                .map(SchRsDto009.Calendar::getCalSeq)
                .collect(Collectors.toList()));

        // method end log
        log.info("confirmAuthorizedCalendars Method Return Data : " + authorizedCalendars);
        log.info("confirmAuthorizedCalendars Method End !!!");

        // return
        return authorizedCalendars;
    }

    // 권한 있는 캘린더 목록 리스트 조회 메소드
    @Override
    public List<SchRsDto009.Calendar> findAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findAuthorizedCalendars Method Start !!!");
        log.info("findAuthorizedCalendars Method Request Data : " + schRqDto009);

        // 권한 있는 캘린더 목록 조회 Mapper 호출
        List<SchRsDto009.Calendar> authorizedCalendars = schMapper009.selectAuthorizedCalendars(schRqDto009);
        // authorizedCalendarList null 및 size 검증
        if (authorizedCalendars == null || authorizedCalendars.size() == 0) {
            log.info("findAuthorizedCalendars Method authorizedCalendars == null or authorizedCalendars.size() == 0");
            return new ArrayList<>();
        }

        // method end log
        log.info("findAuthorizedCalendars Method Return Data : " + authorizedCalendars);
        log.info("findAuthorizedCalendars Method End !!!");

        // return
        return authorizedCalendars;
    }

    // 권한 없는 캘린더 목록 조회 및 schRqDto009 객체 데이터 재 할당 메소드
    @Override
    public List<SchRsDto009.Calendar> confirmUnAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("confirmUnAuthorizedCalendars Method Start !!!");
        log.info("confirmUnAuthorizedCalendars Method Request Data : " + schRqDto009);

        List<SchRsDto009.Calendar> unAuthorizedCalendars = findUnAuthorizedCalendars(schRqDto009);
        if (unAuthorizedCalendars == null || unAuthorizedCalendars.size() == 0) {
            log.info("confirmUnAuthorizedCalendars Method unAuthorizedCalendars == null or unAuthorizedCalendars.size() == 0");
            return new ArrayList<>();
        }
        schRqDto009.setAuthorizedCalendars(unAuthorizedCalendars.stream()
                .map(SchRsDto009.Calendar::getCalSeq)
                .collect(Collectors.toList()));

        // method end log
        log.info("confirmUnAuthorizedCalendars Method Return Data : " + unAuthorizedCalendars);
        log.info("confirmUnAuthorizedCalendars Method End !!!");

        // return
        return unAuthorizedCalendars;
    }

    // 권한 없는 캘린더 목록 리스트 조회 메소드
    @Override
    public List<SchRsDto009.Calendar> findUnAuthorizedCalendars(SchRqDto009 schRqDto009) throws Exception {
        // method start log
        log.info("findUnAuthorizedCalendars Method Start !!!");
        log.info("findUnAuthorizedCalendars Method Request Data : " + schRqDto009);

        // 권한 없는 캘린더 목록 조회 Mapper 호출
        List<SchRsDto009.Calendar> unAuthorizedCalendars = schMapper009.selectUnAuthorizedCalendars(schRqDto009);
        // unAuthorizedCalendarList null 및 size 검증
        if (unAuthorizedCalendars == null || unAuthorizedCalendars.size() == 0) {
            log.info("findUnAuthorizedCalendars Method unAuthorizedCalendars == null or unAuthorizedCalendars.size() == 0");
            return new ArrayList<>();
        }

        // method end log
        log.info("findUnAuthorizedCalendars Method Return Data : " + unAuthorizedCalendars);
        log.info("findUnAuthorizedCalendars Method End !!!");

        // return
        return unAuthorizedCalendars;
    }
}
