package com.example.LightEpro.sch.controller;

import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import com.example.LightEpro.sch.mapper.SchMapper008;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService008;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController008 {
    // service , mapper 선언
    private final SchService008 schService008;
    private final SchMapper008 schMapper008;

    // 단일 캘린더 삭제 API
    @RequestMapping(value = "/sch008", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch008(@RequestBody @Valid SchRqDto008 schRqDto008) throws Exception {
        log.info("sch008 API START !!!");
        log.info("sch008 REQUEST DATA : " + schRqDto008);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto008);
        log.info("sch008 validApiRequest Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch008 API SUCCESS");
        schResponse.setResponseData(schService008.removeCalendarInfo(schRqDto008));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch008 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch008 RESPONSE DATA : " + schResponse);
        log.info("sch008 API END !!!");

        // return
        return schResponse;
    }

    // sch008 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto008 schRqDto008) throws Exception {
        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper008.selectUserCount(schRqDto008);
        if (selectUserCount == 0) {
            log.error("$$$ sch008 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch008 validApiRequest fail !!! (schRqDto008 : " + schRqDto008 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
        // 요청값으로 방은 캘린더 시퀀스 값을 통해 , 캘린더 타입을 조회한다.
        String selectCalendarTypeValue = schMapper008.selectCalendarType(schRqDto008);
        // 캘린더 타입의 값이 빈 값이 경우 Exception 처리
        if (selectCalendarTypeValue.equals(SchConstValue.EMPTY_VALUE)) {
            log.error("$$$ sch008 validApiRequest fail !!! (NotFoundCalException) $$$");
            log.error("$$$ sch008 validApiRequest fail !!! (schRqDto007 : " + schRqDto008 + ") $$$");
            throw new ExceptionCustom.NotFoundCalException();
        }
    }
}
