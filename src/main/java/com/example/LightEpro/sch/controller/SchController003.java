package com.example.LightEpro.sch.controller;

import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper003;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import com.example.LightEpro.sch.service.SchService003;
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
public class SchController003 {
    // service , mapper 선언
    private final SchService003 schService003;
    private final SchMapper003 schMapper003;

    // 단일 일정 삭제 API
    @RequestMapping(value = "/sch003", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch003(@RequestBody @Valid SchRqDto003 schRqDto003) throws Exception{
        log.info("sch003 API START !!!");
        log.info("sch003 REQUEST DATA : " + schRqDto003);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto003);
        log.info("sch003 validApiRequest Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch003 API SUCCESS");
        schResponse.setResponseData(schService003.removeScheduleInfo(schRqDto003));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch003 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch003 RESPONSE DATA : " + schResponse);
        log.info("sch003 API END !!!");

        // return
        return schResponse;
    }

    // sch003 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto003 schRqDto003) throws Exception {
        // 일정 삭제 진행 전 , 요청값으로 받은 일정 시퀀스값을 통해 일정이 존재하는지 판단 후 , 존재하지 않는다면 Exception 처리
        int selectScheduleCountValue = schMapper003.selectScheduleCount(schRqDto003);
        if (selectScheduleCountValue == 0) {
            log.error("$$$ sch003 validApiRequest fail !!! (NotFoundSchException) $$$");
            log.error("$$$ sch003 validApiRequest fail !!! (schRqDto003 : " + schRqDto003 + ") $$$");
            throw new ExceptionCustom.NotFoundSchException();
        }
    }
}
