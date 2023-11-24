package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp008.EmpRqDto008;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService007;
import com.example.LightEpro.emp.service.EmpService008;
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
public class EmpController008 {
    // empService008 선언
    private final EmpService008 empService008;

    // 단일 회사 삭제 API
    @RequestMapping(value = "/emp008", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp008(@RequestBody @Valid EmpRqDto008 empRqDto008) throws Exception {
        log.info("emp008 API Start !!!");
        log.info("emp008 Request Data : " + empRqDto008);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto008);
        log.info("emp008 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp008 API SUCCESS");
        empResponse.setResponseData(empService008.removeCompanyInfo(empRqDto008));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp008 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp008 Response Data : " + empResponse);
        log.info("emp008 API End !!!");

        // return
        return empResponse;
    }

    // emp008 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto008 empRqDto008) throws Exception {

    }
}
