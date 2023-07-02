package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.response.SCH_RESPONSE;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.service.SCH_SERVICE_002;
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
public class SCH_CONTROLLER_002 {

    private final SCH_SERVICE_002 schService002;

    // 단일 일정 수정 API
    @RequestMapping(value = "/SCH_002", method = {RequestMethod.GET, RequestMethod.POST})
    public SCH_RESPONSE SCH_002(@RequestBody @Valid SCH_RQ_DTO_002 schRqDto002) throws Exception{
        SCH_RESPONSE schResponse = new SCH_RESPONSE();

        log.info("SCH_002 API START !!!");
        log.info("SCH_002 REQUEST DATA : " + schRqDto002);

        schResponse.setResponseData(schService002.modifySingleSch(schRqDto002));
        schResponse.setResponseMsg("200");
        schResponse.setReponseCode("SUCCESS");

        log.info("SCH_002 RESPONSE DATA : " + schResponse);
        log.info("SCH_002 API END !!!");

        return schResponse;
    }
}
