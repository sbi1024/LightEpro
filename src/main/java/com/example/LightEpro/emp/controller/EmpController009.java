package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp009.EmpRqDto009;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService004;
import com.example.LightEpro.emp.service.EmpService009;
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
public class EmpController009 {

    private final EmpService009 empService009;

    // 회사 조직도 조회 API
    @RequestMapping(value = "/emp009", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp009(@RequestBody @Valid EmpRqDto009 empRqDto009) throws Exception {
        log.info("emp009 API Start !!!");
        log.info("emp009 Request Data : " + empRqDto009);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto009);
        log.info("emp009 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp009 API SUCCESS");
        empResponse.setResponseData(empService009.findCompList(empRqDto009));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp009 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp009 Response Data : " + empResponse);
        log.info("emp009 API End !!!");

        // return
        return empResponse;
    }

    // emp009 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto009 empRqDto009) throws Exception {

    }
}
