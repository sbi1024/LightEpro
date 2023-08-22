package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService000;
import com.example.LightEpro.sch.service.SchServiceTest;
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
public class SchControllerTest {

    private final SchServiceTest schServiceTest;

    // Sch004 > 일정 목록 조회 API 성능 테스트를 위한 , 대용량 데이터 Insert Test API 구성
    @RequestMapping(value = "/schTest000", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse schTest(@RequestBody SchRqDto000 schRqDto000) throws Exception {
        // TODO 반복문을 통해 호출이 아닌 , 더미데이터 생성 로직 구성 필요
        log.info("schTest Api Start !!!");
        log.info("schTest Request Data : " + schRqDto000);

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("schTest API SUCCESS");
        schResponse.setResponseData(schServiceTest.createTestSch(schRqDto000));

        log.info("schTest Response Data : " + schResponse);
        log.info("schTest Api End !!!");

        return schResponse;
    }

}
