package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp004.EmpRqDto004;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService004;
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
public class EmpController004 {
    private final EmpService004 empService004;

    // 부서 조직도 조회 API
    @RequestMapping(value = "/emp004", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp004(@RequestBody @Valid EmpRqDto004 empRqDto004) throws Exception {
        log.info("emp004 API Start !!!");
        log.info("emp004 Request Data : " + empRqDto004);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto004);
        log.info("emp004 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp004 API SUCCESS");
        empResponse.setResponseData(empService004.findDeptList(empRqDto004));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp004 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp004 Response Data : " + empResponse);
        log.info("emp004 API End !!!");

        // return
        return empResponse;
    }

    // emp004 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto004 empRqDto004) throws Exception {

    }
}
