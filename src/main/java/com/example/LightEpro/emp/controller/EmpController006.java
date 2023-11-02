package com.example.LightEpro.emp.controller;

import com.example.LightEpro.emp.dto.emp006.EmpRqDto006;
import com.example.LightEpro.emp.response.EmpResponse;
import com.example.LightEpro.emp.service.EmpService003;
import com.example.LightEpro.emp.service.EmpService006;
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
public class EmpController006 {

    // empService006  선언
    private final EmpService006 empService006;

    // 단이 회사 상세 조회 API
    @RequestMapping(value = "/emp006", method = {RequestMethod.GET, RequestMethod.POST})
    public EmpResponse emp006(@RequestBody @Valid EmpRqDto006 empRqDto006) throws Exception {
        log.info("emp006 API Start !!!");
        log.info("emp006 Request Data : " + empRqDto006);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(empRqDto006);
        log.info("emp006 validApiRequest Success !!! ");

        // EmpResponse 객체 데이터 생성 및 할당
        EmpResponse empResponse = new EmpResponse();
        empResponse.setResponseStatus("SUCCESS");
        empResponse.setResponseCode(200);
        empResponse.setResponseMsg("emp006 API SUCCESS");
        empResponse.setResponseData(empService006.findCompanyInfo(empRqDto006));

        // stopWatch 종료
        stopWatch.stop();

        log.info("emp006 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("emp006 Response Data : " + empResponse);
        log.info("emp006 API End !!!");

        // return
        return empResponse;
    }

    // emp006 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(EmpRqDto006 empRqDto006) throws Exception {

    }
}
