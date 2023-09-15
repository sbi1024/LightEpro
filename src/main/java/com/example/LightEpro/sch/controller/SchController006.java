package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch006.SchRqDto006;
import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper006;
import com.example.LightEpro.sch.response.SchResponse;
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

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController006 {
    // service , mapper 선언
    private final SchService006 schService006;
    private final SchMapper006 schMapper006;

    // 단일 캘린더 상세 조회 API
    @RequestMapping(value = "/sch006", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch006(@RequestBody @Valid SchRqDto006 schRqDto006) throws Exception {
        log.info("sch006 API START !!!");
        log.info("sch006 REQUEST DATA : " + schRqDto006);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto006);
        log.info("sch006 validApiRequest Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch006 API SUCCESS");
        schResponse.setResponseData(schService006.findCalendarInfo(schRqDto006));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch006 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch006 RESPONSE DATA : " + schResponse);
        log.info("sch006 API END !!!");

        // return
        return schResponse;
    }

    // sch006 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto006 schRqDto006) throws Exception {
        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper006.selectUserCount(schRqDto006);
        if (selectUserCount == 0) {
            log.error("$$$ sch006 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch006 validApiRequest fail !!! (schRqDto006 : " + schRqDto006 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
        // 캘린더 조회 진행 전 , 요청값으로 받은 캘린더 시퀀스값을 통해 캘린더가 존재하는지 판단 후 , 존재하지 않는다면 Exception 처리
        int selectCalendarCountValue = schMapper006.selectCalendarCount(schRqDto006);
        if (selectCalendarCountValue == 0) {
            log.error("$$$ sch006 validApiRequest fail !!! (NotFoundCalException) $$$");
            log.error("$$$ sch006 validApiRequest fail !!! (schRqDto006 : " + schRqDto006 + ") $$$");
            throw new ExceptionCustom.NotFoundCalException();
        }
    }
}
