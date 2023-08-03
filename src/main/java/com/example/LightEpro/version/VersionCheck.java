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
    @RequestMapping("/VERSION_CHECK")
    public SchResponse VersionCheck(){
        String version = "2023_08_02";

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseData(version);

        return schResponse;
    }
}
