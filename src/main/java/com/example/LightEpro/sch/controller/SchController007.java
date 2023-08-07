package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService007;
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
public class SchController007 {
    private final SchService007 schService007;

    // 단일 캘린더 수정 API
    @RequestMapping(value = "/sch007", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch007(@RequestBody @Valid SchRqDto007 schRqDto007) throws Exception {
        log.info("sch007 API START !!!");
        log.info("sch007 REQUEST DATA : " + schRqDto007);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto007);
        log.info("sch007 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch007 API SUCCESS");
        schResponse.setResponseData(schService007.modifySingleCal(schRqDto007));

        stopWatch.stop();

        log.info("sch007 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch007 RESPONSE DATA : " + schResponse);
        log.info("sch007 API END !!!");

        return schResponse;
    }

    // sch007 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto007 schRqDto007) throws Exception {

    }
}

