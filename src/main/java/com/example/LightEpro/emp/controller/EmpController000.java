package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp000.EmpRqDto000;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService000;
import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.response.SchResponse;
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
public class EmpController000 {

    private final EmpService000 empService000;

    // 부서 등록 API
    @RequestMapping(value = "/emp000", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp000(@RequestBody @Valid EmpRqDto000 empRqDto000) throws Exception {
        log.info("emp000 API Start !!!");
        log.info("emp000 Request Data : " + empRqDto000);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto000);
        log.info("emp000 validApiRequest Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp000 API SUCCESS");
        empResponse.setResponseData(empService000.createSingleDept(empRqDto000));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp000 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp000 Response Data : " + empResponse);
        log.info("emp000 API End !!!");

        // return
        return empResponse;
    }

    // emp000 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto000 empRqDto000) throws Exception {

    }
}
