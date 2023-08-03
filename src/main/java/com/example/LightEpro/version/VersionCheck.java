package com.example.LightEpro.version;

import com.example.LightEpro.sch.response.SchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j
public class VersionCheck {
    @RequestMapping("/versionCheck")
    public SchResponse versionCheck(){
        log.info("versionCheck Method Start !!!");

        String version = "2023/08/03 13:20";
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseData(version);

        log.info("versionCheck Method Return Data : " + schResponse);
        log.info("versionCheck Method End !!!");
        return schResponse;
    }
}
