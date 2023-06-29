package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.serviceimpl.SCH_SERVICE_IMPL_001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SCH_CONTROLLER_001 {
    // 생성자를 통한 주입 진행
    private final SCH_SERVICE_IMPL_001 schServiceImpl001;

    // 일정 상세 조회 API
    @RequestMapping(value="/SCH_001", method={RequestMethod.GET, RequestMethod.POST})
    public List<Map<String, Object>> SCH_001(@RequestBody Map<String, Object> request) {
        List<Map<String, Object>> returnData = null;
        try {
            log.info("SCH_001 START !!!");
            log.info("SCH_001 REQUEST : " + request);

            returnData = schServiceImpl001.findSchTitleBySchmSeq(request);

            log.info("SCH_001 RETURN DATA : " + returnData);
            log.info("SCH_001 END !!!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return returnData;
    }
}
