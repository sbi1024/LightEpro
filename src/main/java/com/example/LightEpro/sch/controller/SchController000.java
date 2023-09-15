package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper000;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController000 {
    // service , mapper 선언
    private final SchService000 schService000;
    private final SchMapper000 schMapper000;

    // 단일 일정 등록 API
    @RequestMapping(value = "/sch000", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch000(@RequestBody @Valid SchRqDto000 schRqDto000) throws Exception {
        log.info("sch000 API Start !!!");
        log.info("sch000 Request Data : " + schRqDto000);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto000);
        log.info("sch000 validApiRequest Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch000 API SUCCESS");
        schResponse.setResponseData(schService000.createScheduleInfo(schRqDto000));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch000 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch000 Response Data : " + schResponse);
        log.info("sch000 API End !!!");

        // return
        return schResponse;
    }

    // sch000 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto000 schRqDto000) throws Exception {
        // schRqDto000 객체 데이터 추출
        SchRqDto000.Schedule schedule = schRqDto000.getSchedule();
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();

        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper000.selectUserCount(schRqDto000);
        if (selectUserCount == 0) {
            log.error("$$$ sch000 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch000 validApiRequest fail !!! (schRqDto000 : " + schRqDto000 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
        // 시작일자 값이 , 종료일자보다 큰 경우 Exception 처리
        if (schedule.getStartDate().isAfter(schedule.getEndDate())) {
            log.error("$$$ sch000 validApiRequest fail !!! (NotBeGreaterThanEndDateException) $$$");
            log.error("$$$ sch000 validApiRequest fail !!! (schRqDto000 : " + schRqDto000 + ") $$$");
            throw new ExceptionCustom.NotBeGreaterThanEndDateException();
        }
        // 요청값으로 받은 캘린더시퀀스 값을통해 , 캘린더 타입을 조회한다.
        String selectCalendarTypeValue = schMapper000.selectCalendarType(schRqDto000);
        // 캘린더 타입의 값이 빈 값인 경우 Exception 처리
        if (selectCalendarTypeValue.equals(SchConstValue.EMPTY_VALUE)) {
            log.error("$$$ sch000 validApiRequest fail !!! (NotFoundCalException) $$$");
            log.error("$$$ sch000 validApiRequest fail !!! (schRqDto000 : " + schRqDto000 + ") $$$");
            throw new ExceptionCustom.NotFoundCalException();
        }
        // 개인캘린더 등록시에 , 공개범위 데이터가 포함되는 경우 Exception 처리 (disclosureScopes 값이 존재해선 안됨)
        if (selectCalendarTypeValue.equals(SchConstValue.ECAL_TYPE) && (disclosureScopes != null)) {
            log.error("$$$ sch000 validApiRequest fail !!! (NotBeIncludedDisclosureException) $$$");
            log.error("$$$ sch000 validApiRequest fail !!! (schRqDto000 : " + schRqDto000 + ") $$$");
            throw new ExceptionCustom.NotBeIncludedDisclosureException();
        }
    }
}
