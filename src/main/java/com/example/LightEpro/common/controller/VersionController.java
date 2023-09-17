package com.example.LightEpro.common.controller;

import com.example.LightEpro.sch.response.SchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j
public class VersionController {
    @RequestMapping("/version")
    public String version() {
        log.info("version Method Start !!!");

        String version = "2023/09/15 15:53";

        log.info("version Method Return Data : " + version);
        log.info("version Method End !!!");

        return version;
    }
}
