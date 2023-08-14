package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService005;
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
public class SchController005 {
    private final SchService005 schService005;

    // 캘린더 등록 API
    @RequestMapping(value = "/sch005", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch005(@RequestBody @Valid SchRqDto005 schRqDto005) throws Exception {
        log.info("sch005 API START !!!");
        log.info("sch005 REQUEST DATA : " + schRqDto005);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto005);
        log.info("sch005 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch005 API SUCCESS");
        schResponse.setResponseData(schService005.createSingleCal(schRqDto005));

        stopWatch.stop();

        log.info("sch005 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch005 RESPONSE DATA : " + schResponse);
        log.info("sch005 API END !!!");

        return schResponse;
    }

    // sch005 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto005 schRqDto005) throws Exception {
        SchRqDto005.Emp emp = schRqDto005.getEmp();
        SchRqDto005.Calendar calender = schRqDto005.getCalendar();
        SchRqDto005.Owner owner = schRqDto005.getOwner();
        List<SchRqDto005.Manager> managers = schRqDto005.getManagers();
        // 사원 시퀀스 추출
        int empSeq = emp.getEmpSeq();
        // 캘린더 타입 추출
        String calType = calender.getCalType();
        // 소유자 시퀀스 추출
        int ownerCdeSeq = owner.getCdeSeq();

        // 1. 개인캘린더 등록시에 , 관리자 요청값이 포함되어 들어오는 경우 Exception
        if (calType.equals(SchConstValue.ECAL_TYPE) && managers != null) {
            log.error("$$$ sch005 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.IncorrectIncludException();
        }

        // 2. 캘린더 등록시에 , 캘린더의 소유자 cdeSeq 값은 요청자의 empSeq 값과 일치해야 한다.
        if (empSeq != ownerCdeSeq) {
            log.error("$$$ sch005 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.IncorrectIncludException();
        }
    }
}
