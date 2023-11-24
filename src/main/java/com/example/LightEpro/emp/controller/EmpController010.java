package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp010.EmpRqDto010;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService010;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class EmpController010 {
    // empService010 선언
    private final EmpService010 empService010;

    // 직책/직위/직급 등록 API
    @RequestMapping(value = "/emp010", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp010(@RequestBody @Valid EmpRqDto010 empRqDto010) throws Exception {
        log.info("emp010 API Start !!!");
        log.info("emp010 Request Data : " + empRqDto010);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto010);
        log.info("emp010 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp010 API SUCCESS");
        empResponse.setResponseData(empService010.createPositionInfo(empRqDto010));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp010 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp010 Response Data : " + empResponse);
        log.info("emp010 API End !!!");

        // return
        return empResponse;
    }

    // emp010 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto010 empRqDto010) throws Exception {

    }
}
