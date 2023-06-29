package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.dto.SCH_RS_DTO_000;
import com.example.LightEpro.sch.service.SCH_SERVICE_000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SCH_CONTROLLER_000 {

    private final SCH_SERVICE_000 schService000;

    // 일정 등록 API
    @RequestMapping(value = "/SCH_000", method = {RequestMethod.GET, RequestMethod.POST})
    public SCH_RS_DTO_000 SCH_000(@RequestBody @Valid SCH_RQ_DTO_000 schRqDto000) {
        SCH_RS_DTO_000 schRsDto000 = new SCH_RS_DTO_000();
        try {
            log.info("SCH_000 API START !!!");
            log.info("SCH_000 REQUEST DATA : " + schRqDto000);

            schRsDto000.setResultCode("200");
            schRsDto000.setResultMsg("SUCCESS");
            schRsDto000.setResultData(schService000.createSingleSch(schRqDto000));

            log.info("SCH_000 RESPONSE DATA : " + schRsDto000);
            log.info("SCH_000 API END !!!");
        } catch (Exception e) {
            schRsDto000.setResultCode("");
            schRsDto000.setResultMsg("FAIL");

            e.printStackTrace();
            e.getMessage();
            log.error(e.getMessage());
        }
        return schRsDto000;
    }
}
