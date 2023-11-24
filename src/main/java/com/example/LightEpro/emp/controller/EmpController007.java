package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp007.EmpRqDto007;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService007;
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
public class EmpController007 {

    // empService007 선언
    private final EmpService007 empService007;

    // 단일 회사 수정 API
    @RequestMapping(value = "/emp007", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp007(@RequestBody @Valid EmpRqDto007 empRqDto007) throws Exception {
        log.info("emp007 API Start !!!");
        log.info("emp007 Request Data : " + empRqDto007);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto007);
        log.info("emp007 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp007 API SUCCESS");
        empResponse.setResponseData(empService007.modifyCompanyInfo(empRqDto007));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp007 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp007 Response Data : " + empResponse);
        log.info("emp007 API End !!!");

        // return
        return empResponse;
    }

    // emp007 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto007 empRqDto007) throws Exception {

    }
}
