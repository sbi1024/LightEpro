package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.dto.sch999.SchRqDto999;
import com.example.LightEpro.sch.helper.SchAuthorityHelper;
import com.example.LightEpro.sch.mapper.SchMapper001;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController001 {
    // service , mapper 선언
    private final SchService001 schService001;
    private final SchMapper001 schMapper001;
    private final SchAuthorityHelper schAuthorityHelper;

    // 단일 일정 상세 조회 API
    @RequestMapping(value = "/sch001", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch001(@RequestBody @Valid SchRqDto001 schRqDto001) throws Exception {
        log.info("sch001 API START !!!");
        log.info("SCH_001 REQUEST DATA : " + schRqDto001);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto001);
        log.info("sch001 validApiRequest Success !!! ");
        // API 권한 검사 메소드 호출
        validApiAuthority(schRqDto001);
        log.info("sch001 validApiAuthority Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch001 API SUCCESS");
        schResponse.setResponseData(schService001.findScheduleInfo(schRqDto001));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch001 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch001 RESPONSE DATA : " + schResponse);
        log.info("sch001 API END !!!");

        // return
        return schResponse;
    }


    // sch001 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto001 schRqDto001) throws Exception {
        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper001.selectUserCount(schRqDto001);
        if (selectUserCount == 0) {
            log.error("$$$ sch001 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch001 validApiRequest fail !!! (schRqDto001 : " + schRqDto001 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
        // 일정 조회 진행 전 , 요청값으로 받은 일정 시퀀스값을 통해 일정이 존재하는지 판단 후 , 존재하지 않는다면 Exception 처리
        int selectScheduleCountValue = schMapper001.selectScheduleCount(schRqDto001);
        if (selectScheduleCountValue == 0) {
            log.error("$$$ sch001 validApiRequest fail !!! (NotFoundSchException) $$$");
            log.error("$$$ sch001 validApiRequest fail !!! (schRqDto001 : " + schRqDto001 + ") $$$");
            throw new ExceptionCustom.NotFoundSchException();
        }
    }

    private void validApiAuthority(SchRqDto001 schRqDto001) throws Exception {
        // TODO 해당 부분 추가 수정 및 확장 필요
        ModelMapper modelMapper = new ModelMapper();
        SchRqDto999 schRqDto999 = modelMapper.map(schRqDto001, SchRqDto999.class);
        schRqDto999.setModuleApiType(SchConstValue.SCHEDULE_TYPE);
        schRqDto999.setModuleApiPersonality(SchConstValue.FIND_PERSONALITY);
        boolean authority = schAuthorityHelper.confirmAuthorityInfo(schRqDto999);
        if (!authority) {
            log.error("$$$ sch001 validApiAuthority fail !!! () $$$");
            log.error("$$$ sch001 validApiAuthority fail !!! (schRqDto001 : " + schRqDto001 + ") $$$");
            throw new Exception("조회 권한이 존재하지 않습니다.");
        }
    }
}
