package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp015.EmpRqDto015;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService015;
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
public class EmpController015 {
    private final EmpService015 empService015;

    // 사원 등록 API
    @RequestMapping(value = "/emp015", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp015(@RequestBody @Valid EmpRqDto015 empRqDto015) throws Exception {
        log.info("emp015 API Start !!!");
        log.info("emp015 Request Data : " + empRqDto015);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto015);
        log.info("emp015 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp015 API SUCCESS");
        empResponse.setResponseData(empService015.createSingleEmp(empRqDto015));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp015 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp015 Response Data : " + empResponse);
        log.info("emp015 API End !!!");

        // return
        return empResponse;
    }

    // emp015 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto015 empRqDto015) throws Exception {

    }
}
