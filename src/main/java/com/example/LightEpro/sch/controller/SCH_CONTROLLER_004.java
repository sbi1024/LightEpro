package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch_004.SCH_RQ_DTO_004;
import com.example.LightEpro.sch.response.SCH_RESPONSE;
import com.example.LightEpro.sch.service.SCH_SERVICE_004;
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
public class SCH_CONTROLLER_004 {

    private final SCH_SERVICE_004 schService004;

    // 일정 목록 조회 API
    @RequestMapping(value = "/SCH_004", method = {RequestMethod.GET, RequestMethod.POST})
    public SCH_RESPONSE SCH_004(@RequestBody @Valid SCH_RQ_DTO_004 schRqDto004) throws Exception {
        log.info("SCH_004 API START !!!");
        log.info("SCH_004 REQUEST DATA : " + schRqDto004);

        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("SCH_004 API SUCCESS");
        schResponse.setResponseData(schService004.selectSchList(schRqDto004));

        log.info("SCH_004 RESPONSE DATA : " + schResponse);
        log.info("SCH_004 API END !!!");

        return schResponse;
    }
}
