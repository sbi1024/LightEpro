package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.ConstValue;
import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService004;
import com.example.LightEpro.sch.service.SchService005;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController005 {
    private final SchService005 schService005;

    // 캘린더 등록 API
    @RequestMapping(value = "/sch005", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch005(@RequestBody @Valid SchRqDto005 schRqDto005) throws Exception {
        log.info("sch005 API START !!!");
        log.info("sch005 REQUEST DATA : " + schRqDto005);

        validApiRequest(schRqDto005);
        log.info("sch005 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch005 API SUCCESS");
        schResponse.setResponseData(schService005.createCalendar(schRqDto005));

        log.info("sch005 RESPONSE DATA : " + schResponse);
        log.info("sch005 API END !!!");

        return schResponse;
    }

    // sch005 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto005 schRqDto005) throws Exception {
        SchRqDto005.Emp emp = schRqDto005.getEmp();
        SchRqDto005.Calender calender = schRqDto005.getCalender();
        SchRqDto005.Owner owner = schRqDto005.getOwner();
        List<SchRqDto005.Manager> managers = schRqDto005.getManagers();
        // 사원 시퀀스 추출
        int empSeq = schRqDto005.getEmp().getEmpSeq();
        // 캘린더 타입 추출
        String calType = calender.getCalType();
        // 소유자 시퀀스 추출
        int ownerCdeSeq = owner.getCdeSeq();
        // 소유자 시퀀스 타입 추출
        String ownerCdeType = owner.getCdeType();


        // 1. 개인캘린더 등록시에 , 관리자 요청값이 포함되어 들어오는 경우 Exception
        if (calType.equals(ConstValue.ECAL_TYPE) && managers != null) {
            log.error("$$$ sch005 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.IncorrectIncludException();
        }

        // 2. 캘린더 등록시에 , 캘린더의 소유자 cdeSeq 값은 요청자의 empSeq 값과 일치해야 한다.
        if (empSeq != ownerCdeSeq) {
            log.error("$$$ sch005 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.IncorrectIncludException();
        }
    }

    // TODO > 구성해야할 API 목록
    // 1. 캘린더 조회
    // 2. 캘린더 삭제
    // 3. 캘린더 수정
    // 4. 캘린더 목록 조회 (내)
    // 이후 권한 관련 이슈 체크 및 부서 및 회사 관련 처리를 위한 EMP 쪽 작업 진행
    // EMP 완료되는 시점에서 프론트작업 진행 필요
    // 반복일정 어떤식으로 정책확립할지 확인 필요
}
