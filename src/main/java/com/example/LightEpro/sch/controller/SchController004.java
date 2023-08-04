package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService004;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController004 {

    private final SchService004 schService004;

    // 일정 목록 조회 API
    @RequestMapping(value = "/sch004", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch004(@RequestBody @Valid SchRqDto004 schRqDto004) throws Exception {
        log.info("sch004 API START !!!");
        log.info("sch004 REQUEST DATA : " + schRqDto004);

        validApiRequest(schRqDto004);
        log.info("sch004 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch004 API SUCCESS");
        // TODO 실행 결과 확인 필요
        schResponse.setResponseData(schService004.selectSchList(schRqDto004));

        log.info("sch004 RESPONSE DATA : " + schResponse);
        log.info("sch004 API END !!!");

        return schResponse;
    }

    // SCH_001 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto004 schRqDto004) throws Exception {
        List<Integer> calSeqs = schRqDto004.getCal().getCalSeqs();
        if (calSeqs == null || calSeqs.size() == 0) {
            // TODO 요청값의 캘린더 시퀀스 사이즈 체크 필요 및 Exception 처리
        }
    }
}
