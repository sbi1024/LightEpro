package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.service.SCH_SERVICE_000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SCH_CONTROLLER_000 {

    private final SCH_SERVICE_000 schService000;

    // 일정 등록 API
    @RequestMapping(value="/SCH_000", method={RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> SCH_000(@RequestBody Map<String, Object> request){
        Map<String, Object> returnData = null;
        try {
            log.info("SCH_000 START !!!");
            log.info("SCH_000 REQUEST DATA : " + request);

            returnData = schService000.insertSingleSch(request);

            log.info("SCH_000 RETURN DATA : " + returnData);
            log.info("SCH_000 END !!!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            log.error(e.toString());
        }
        return returnData;
    }
}
