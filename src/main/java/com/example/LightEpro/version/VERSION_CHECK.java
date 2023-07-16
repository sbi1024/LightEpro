package com.example.LightEpro.version;

import com.example.LightEpro.sch.response.SCH_RESPONSE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@Slf4j
public class VERSION_CHECK {
    @RequestMapping("/VERSION_CHECK")
    public SCH_RESPONSE VersionCheck(){
        String version = "2023_07_16";

        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        schResponse.setResponseData(version);

        return schResponse;
    }
}
