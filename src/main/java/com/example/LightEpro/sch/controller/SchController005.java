package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService004;
import com.example.LightEpro.sch.service.SchService005;
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
public class SchController005 {
    private final SchService005 schService005;

    // 캘린더 등록 API
    @RequestMapping(value = "/sch005", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch005(@RequestBody @Valid SchRqDto005 schRqDto005) throws Exception {
        log.info("sch005 API START !!!");
        log.info("sch005 REQUEST DATA : " + schRqDto005);

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch005 API SUCCESS");
        schResponse.setResponseData(schService005.createCalendar(schRqDto005));

        log.info("sch005 RESPONSE DATA : " + schResponse);
        log.info("sch005 API END !!!");

        return schResponse;
    }
}
