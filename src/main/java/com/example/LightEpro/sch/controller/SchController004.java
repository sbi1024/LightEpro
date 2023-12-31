package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.exception.ExceptionCustom;
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
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController004 {

    private final SchService004 schService004;

    // 월 기준 일정 목록 조회 API
    @RequestMapping(value = "/sch004", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch004(@RequestBody @Valid SchRqDto004 schRqDto004) throws Exception {
        log.info("sch004 API START !!!");
        log.info("sch004 REQUEST DATA : " + schRqDto004);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto004);
        log.info("sch004 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch004 API SUCCESS");
        schResponse.setResponseData(schService004.findSchList(schRqDto004));

        stopWatch.stop();

        log.info("sch004 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch004 RESPONSE DATA : " + schResponse);
        log.info("sch004 API END !!!");

        return schResponse;
    }

    // sch004 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto004 schRqDto004) throws Exception {
        // 일정 목록 리스트 조회 전 , 요청값의 캘린더 시퀀스 리스트 값의 길이 판단 후 , 0인 경우 Exception 처리
        List<Integer> calSeqs = schRqDto004.getCalendar().getCalSeqs();
        if (calSeqs.size() == 0) {
            log.error("$$$ sch004 validApiRequest fail !!! (NotValidCalSeqsException) $$$");
            log.error("$$$ sch004 validApiRequest fail !!! (schRqDto004 : " + schRqDto004 + ") $$$");
            throw new ExceptionCustom.NotValidCalSeqsException();
        }
    }
}
