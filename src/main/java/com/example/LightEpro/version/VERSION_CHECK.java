package com.example.LightEpro.version;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j
public class VERSION_CHECK {
    @RequestMapping("/VERSION_CHECK")
    public String VersionCheck(){
        String version = "2023_07_01";
        log.trace(version);
        log.debug(version);
        log.info(version);
        log.warn(version);
        log.error(version);
        return version;
    }
}
