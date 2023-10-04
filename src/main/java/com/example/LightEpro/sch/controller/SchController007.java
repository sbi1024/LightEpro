package com.example.LightEpro.sch.controller;

import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch999.SchRqDto999;
import com.example.LightEpro.sch.helper.SchAuthorityHelper;
import com.example.LightEpro.sch.mapper.SchMapper007;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService007;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
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
public class SchController007 {
    // service , mapper , schAuthorityHelper 선언
    private final SchService007 schService007;
    private final SchMapper007 schMapper007;
    private final SchAuthorityHelper schAuthorityHelper;

    // 단일 캘린더 수정 API
    @RequestMapping(value = "/sch007", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch007(@RequestBody @Valid SchRqDto007 schRqDto007) throws Exception {
        log.info("sch007 API START !!!");
        log.info("sch007 REQUEST DATA : " + schRqDto007);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto007);
        log.info("sch007 validApiRequest Success !!! ");
        // API 권한 검사 메소드 호출
        validApiAuthority(schRqDto007);
        log.info("sch007 validApiAuthority Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch007 API SUCCESS");
        schResponse.setResponseData(schService007.modifyCalendarInfo(schRqDto007));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch007 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch007 RESPONSE DATA : " + schResponse);
        log.info("sch007 API END !!!");

        // return
        return schResponse;
    }

    // sch007 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto007 schRqDto007) throws Exception {
        // schRqDto007 객체 데이터 추출
        SchRqDto007.Calendar calendar = schRqDto007.getCalendar();
        SchRqDto007.Owner owner = schRqDto007.getOwner();
        List<SchRqDto007.Manager> managers = schRqDto007.getManagers();

        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper007.selectUserCount(schRqDto007);
        if (selectUserCount == 0) {
            log.error("$$$ sch007 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch007 validApiRequest fail !!! (schRqDto007 : " + schRqDto007 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
        // 요청값으로 받은 캘린더 시퀀스 값을통해 , 캘린더 타입을 조회한다.
        int selectCalendarCountValue = schMapper007.selectCalendarCount(schRqDto007);
        // 캘린더 타입의 값이 빈 값이 경우 Exception 처리
        if (selectCalendarCountValue == 0) {
            log.error("$$$ sch007 validApiRequest fail !!! (NotFoundCalException) $$$");
            log.error("$$$ sch007 validApiRequest fail !!! (schRqDto007 : " + schRqDto007 + ") $$$");
            throw new ExceptionCustom.NotFoundCalException();
        }
        // 캘린더 수정시에 , 캘린더의 소유자 cdeSeq 값이 기존 캘린더 소유자의의 cdeSeq 일치하지 않거나 소유자의 타입이 E가 아닌 경우 Exception 처리
        int selectOriginOwnerValue = schMapper007.selectOriginOwner(schRqDto007);
        if ((selectOriginOwnerValue != owner.getCdeSeq()) ||
                (!SchConstValue.CDE_I_TYPE.equals(owner.getCdeType()))) {
            log.error("$$$ sch007 validApiRequest fail !!! (NotFoundOwnerException) $$$");
            log.error("$$$ sch007 validApiRequest fail !!! (schRqDto007 : " + schRqDto007 + ") $$$");
            throw new ExceptionCustom.NotFoundOwnerException();
        }
        // 개인 캘린더 타입에서의 캘린더 수정 진행시 , 관리자 데이터가 포함되는 경우 Exception 처리
        if (calendar.getCalType().equals(SchConstValue.CAL_I_TYPE) && (managers != null)) {
            log.error("$$$ sch007 validApiRequest fail !!! (NotBeIncludedManagerException) $$$");
            log.error("$$$ sch007 validApiRequest fail !!! (schRqDto007 : " + schRqDto007 + ") $$$");
            throw new ExceptionCustom.NotBeIncludedManagerException();
        }
    }

    // sch007 API 권한 검증 진행
    private void validApiAuthority(SchRqDto007 schRqDto007) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        SchRqDto999 schRqDto999 = modelMapper.map(schRqDto007, SchRqDto999.class);
        schRqDto999.setModuleApiType(SchConstValue.CALENDAR_TYPE);
        schRqDto999.setModuleApiPersonality(SchConstValue.MODIFY_PERSONALITY);
        boolean authority = schAuthorityHelper.confirmAuthorityInfo(schRqDto999);
        if (!authority) {
            log.error("$$$ sch007 validApiAuthority fail !!! (NotAuthorizedForCalModifyException) $$$");
            log.error("$$$ sch007 validApiAuthority fail !!! (schRqDto007 : " + schRqDto007 + ") $$$");
            throw new ExceptionCustom.NotAuthorizedForCalModifyException();
        }
    }
}

