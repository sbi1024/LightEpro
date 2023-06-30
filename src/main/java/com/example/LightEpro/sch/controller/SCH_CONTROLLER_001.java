package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.SCH_RESPONSE;
import com.example.LightEpro.sch.service.SCH_SERVICE_001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SCH_CONTROLLER_001 {

    private final SCH_SERVICE_001 schService001;

    // 일정 상세 조회 API
    @RequestMapping(value = "/SCH_001", method = {RequestMethod.GET, RequestMethod.POST})
    public SCH_RESPONSE SCH_001(@RequestBody @Valid SCH_RQ_DTO_001 schRqDto001) {
        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        try {
            log.info("SCH_001 API START !!!");
            log.info("SCH_001 REQUEST DATA : " + schRqDto001);

            schResponse.setResponseData(schService001.findSingleSch(schRqDto001));
            schResponse.setResponseMsg("200");
            schResponse.setReponseCode("SUCCESS");

            log.info("SCH_001 RESPONSE DATA : " + schResponse);
            log.info("SCH_001 API END !!!");
        } catch (Exception e) {
            log.error(e.getMessage());

            schResponse.setResponseMsg("");
            schResponse.setReponseCode("FAIL");

            e.printStackTrace();
            e.getMessage();
        }
        return schResponse;
    }
}
