package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import com.example.LightEpro.sch.service.SchService003;
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
public class SchController003 {

    private final SchService003 schService003;

    // 단일 일정 삭제 API
    @RequestMapping(value = "/SCH_003", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse SCH_003(@RequestBody @Valid SchRqDto003 schRqDto003) throws Exception{
        log.info("SCH_003 API START !!!");
        log.info("SCH_003 REQUEST DATA : " + schRqDto003);

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("SCH_003 API SUCCESS");
        schResponse.setResponseData(schService003.deleteSingleSch(schRqDto003));

        log.info("SCH_003 RESPONSE DATA : " + schResponse);
        log.info("SCH_003 API END !!!");

        return schResponse;
    }
}
