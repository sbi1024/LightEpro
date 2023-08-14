package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
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
    private final SchService008 schService008;

    // 단일 캘린더 삭제 API
    @RequestMapping(value = "/sch008", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch008(@RequestBody @Valid SchRqDto008 schRqDto008) throws Exception {
        log.info("sch008 API START !!!");
        log.info("sch008 REQUEST DATA : " + schRqDto008);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto008);
        log.info("sch008 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch008 API SUCCESS");
        schResponse.setResponseData(schService008.removeSingleCal(schRqDto008));

        stopWatch.stop();

        log.info("sch008 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch008 RESPONSE DATA : " + schResponse);
        log.info("sch008 API END !!!");

        return schResponse;
    }

    // sch008 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto008 schRqDto008) throws Exception {
        // TODO
    }
}
