package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp001.EmpRqDto001;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService001;
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
public class EmpController001 {

    private final EmpService001 empService001;

    // 부서 수정 API
    @RequestMapping(value = "/emp001", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp001(@RequestBody @Valid EmpRqDto001 empRqDto001) throws Exception {
        log.info("emp001 API Start !!!");
        log.info("emp001 Request Data : " + empRqDto001);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto001);
        log.info("emp001 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp001 API SUCCESS");
        empResponse.setResponseData(empService001.modifySingleDept(empRqDto001));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp001 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp001 Response Data : " + empResponse);
        log.info("emp001 API End !!!");

        // return
        return empResponse;
    }

    // emp001 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto001 empRqDto001) throws Exception {

    }
}
