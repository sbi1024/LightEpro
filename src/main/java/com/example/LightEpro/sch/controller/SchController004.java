package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.dto.sch009.SchRsDto009;
import com.example.LightEpro.sch.mapper.SchMapper004;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService004;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController004 {
    // service , mapper 선언
    private final SchService004 schService004;
    private final SchMapper004 schMapper004;

    // 월 기준 일정 목록 조회 API
    @RequestMapping(value = "/sch004", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch004(@RequestBody @Valid SchRqDto004 schRqDto004) throws Exception {
        log.info("sch004 API START !!!");
        log.info("sch004 REQUEST DATA : " + schRqDto004);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto004);
        log.info("sch004 validApiRequest Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch004 API SUCCESS");
        schResponse.setResponseData(schService004.findSchedulesInfo(schRqDto004));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch004 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch004 RESPONSE DATA : " + schResponse);
        log.info("sch004 API END !!!");

        // return
        return schResponse;
    }

    // sch004 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto004 schRqDto004) throws Exception {
        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper004.selectUserCount(schRqDto004);
        if (selectUserCount == 0) {
            log.error("$$$ sch004 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch004 validApiRequest fail !!! (schRqDto004 : " + schRqDto004 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
        // 일정 목록 리스트 조회 전 , 요청값의 캘린더 시퀀스 리스트 값의 길이 판단 후 , 0인 경우 Exception 처리
        List<Integer> authorizedCalendarSequences = schRqDto004.getCalendar().getAuthorizedCalendarSequences();
        List<Integer> unAuthorizedCalendarSequences = schRqDto004.getCalendar().getUnAuthorizedCalendarSequences();
        // calendarSequences = authorizedCalendarSequences + unAuthorizedCalendarSequences
        List<Integer> calendarSequences = Stream.of(authorizedCalendarSequences, unAuthorizedCalendarSequences)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        if (calendarSequences.size() == 0) {
            log.error("$$$ sch004 validApiRequest fail !!! (NotIncludedCalendarSequencesException) $$$");
            log.error("$$$ sch004 validApiRequest fail !!! (schRqDto004 : " + schRqDto004 + ") $$$");
            throw new ExceptionCustom.NotIncludedCalendarSequencesException();
        }
        // TODO 권한있는 캘린더 요청값인지 혹은 권한없는 캘린더 시퀀스 값인지 검증 필요
    }
}
