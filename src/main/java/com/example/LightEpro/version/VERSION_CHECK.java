package com.example.LightEpro.version;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class VERSION_CHECK {
    @RequestMapping("/VERSION_CHECK")
    public String VersionCheck(){
        String version = "2023_06_25";
        return version;
    }
}
