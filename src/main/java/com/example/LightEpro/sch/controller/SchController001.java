package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper000;
import com.example.LightEpro.sch.mapper.SchMapper001;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController001 {

    private final SchService001 schService001;
    private final SchMapper001 schMapper001;

    // 단일 일정 상세 조회 API
    @RequestMapping(value = "/sch001", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch001(@RequestBody @Valid SchRqDto001 schRqDto001) throws Exception{
        log.info("sch001 API START !!!");
        log.info("SCH_001 REQUEST DATA : " + schRqDto001);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto001);
        log.info("sch001 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch001 API SUCCESS");
        schResponse.setResponseData(schService001.findSingleSch(schRqDto001));


        stopWatch.stop();
        log.info("sch001 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch001 RESPONSE DATA : " + schResponse);
        log.info("sch001 API END !!!");

        return schResponse;
    }

    // sch001 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto001 schRqDto001) throws Exception {
        // 일정 조회 진행 전 , 요청값으로 받은 일정 시퀀스값을 통해 일정이 존재하는지 판단 후 , 존재하지 않는다면 Exception 처리
        int schCnt = schMapper001.checkSchExist(schRqDto001);
        if (schCnt == 0) {
            log.error("$$$ sch001 validApiRequest fail !!! (NotFountSchException) $$$");
            log.error("$$$ sch001 validApiRequest fail !!! (schRqDto001 : " + schRqDto001 + ") $$$");
            throw new ExceptionCustom.NotFountSchException();
        }
    }
}
