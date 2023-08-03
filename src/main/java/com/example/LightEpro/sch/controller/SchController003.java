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
    @RequestMapping(value = "/sch003", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch003(@RequestBody @Valid SchRqDto003 schRqDto003) throws Exception{
        log.info("sch003 API START !!!");
        log.info("sch003 REQUEST DATA : " + schRqDto003);

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch003 API SUCCESS");
        schResponse.setResponseData(schService003.deleteSingleSch(schRqDto003));

        log.info("sch003 RESPONSE DATA : " + schResponse);
        log.info("sch003 API END !!!");

        return schResponse;
    }
}
