package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController001 {

    private final SchService001 schService001;

    // 단일 일정 상세 조회 API
    @RequestMapping(value = "/SCH_001", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse SCH_001(@RequestBody @Valid SchRqDto001 schRqDto001) throws Exception{
        log.info("SCH_001 API START !!!");
        log.info("SCH_001 REQUEST DATA : " + schRqDto001);

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("SCH_001 API SUCCESS");
        schResponse.setResponseData(schService001.findSingleSch(schRqDto001));

        log.info("SCH_001 RESPONSE DATA : " + schResponse);
        log.info("SCH_001 API END !!!");

        return schResponse;
    }
}
