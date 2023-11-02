package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp003.EmpRqDto003;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService003;
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
public class EmpController003 {

    // empService003 선언
    private final EmpService003 empService003;

    // 단일 부서 삭제 API
    @RequestMapping(value = "/emp003", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp003(@RequestBody @Valid EmpRqDto003 empRqDto003) throws Exception {
        log.info("emp003 API Start !!!");
        log.info("emp003 Request Data : " + empRqDto003);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto003);
        log.info("emp003 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp003 API SUCCESS");
        empResponse.setResponseData(empService003.removeDepartmentInfo(empRqDto003));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp003 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp003 Response Data : " + empResponse);
        log.info("emp003 API End !!!");

        // return
        return empResponse;
    }

    // emp003 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto003 empRqDto003) throws Exception {

    }
}
