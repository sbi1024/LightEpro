package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.response.SCH_RESPONSE;
import com.example.LightEpro.sch.service.SCH_SERVICE_000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SCH_CONTROLLER_000 {

    private final SCH_SERVICE_000 schService000;

    // 단일 일정 등록 API
    @RequestMapping(value = "/SCH_000", method = {RequestMethod.GET, RequestMethod.POST})
    public SCH_RESPONSE SCH_000(@RequestBody @Valid SCH_RQ_DTO_000 schRqDto000) throws Exception{
        SCH_RESPONSE schResponse = new SCH_RESPONSE();

        log.info("SCH_000 API START !!!");
        log.info("SCH_000 REQUEST DATA : " + schRqDto000);

        schResponse.setResponseData(schService000.createSingleSch(schRqDto000));
        schResponse.setReponseCode("200");
        schResponse.setResponseMsg("SUCCESS");

        log.info("SCH_000 RESPONSE DATA : " + schResponse);
        log.info("SCH_000 API END !!!");

        return schResponse;
    }
    
    // 일정 등록시 요청값 재 검증 진행
}
