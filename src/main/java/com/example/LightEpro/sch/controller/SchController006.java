package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.ConstValue;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch006.SchRqDto006;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper006;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService005;
import com.example.LightEpro.sch.service.SchService006;
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
public class SchController006 {
    private final SchService006 schService006;
    private final SchMapper006 schMapper006;

    // 단일 캘린더 상세 조회 API
    @RequestMapping(value = "/sch006", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch006(@RequestBody @Valid SchRqDto006 schRqDto006) throws Exception {
        log.info("sch006 API START !!!");
        log.info("sch006 REQUEST DATA : " + schRqDto006);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto006);
        log.info("sch006 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch006 API SUCCESS");
        schResponse.setResponseData(schService006.findSingleCal(schRqDto006));

        stopWatch.stop();

        log.info("sch006 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch006 RESPONSE DATA : " + schResponse);
        log.info("sch006 API END !!!");

        return schResponse;
    }

    // sch006 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto006 schRqDto006) throws Exception {
        // 캘린더 조회 진행 전 , 요청값으로 받은 캘린더 시퀀스값을 통해 캘린더가 존재하는지 판단 후 , 존재하지 않는다면 Exception 처리
        int calCnt = schMapper006.checkCalExist(schRqDto006);
        if(calCnt == 0){
            log.error("$$$ sch006 validApiRequest fail !!! (NotFoundCalException) $$$");
            log.error("$$$ sch006 validApiRequest fail !!! (schRqDto006 : " + schRqDto006 + ") $$$");
            throw new ExceptionCustom.NotFoundCalException();
        }
    }
}
