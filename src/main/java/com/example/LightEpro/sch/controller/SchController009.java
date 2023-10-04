package com.example.LightEpro.sch.controller;

import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import com.example.LightEpro.sch.dto.sch009.SchRqDto009;
import com.example.LightEpro.sch.dto.sch999.SchRqDto999;
import com.example.LightEpro.sch.helper.SchAuthorityHelper;
import com.example.LightEpro.sch.mapper.SchMapper009;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService008;
import com.example.LightEpro.sch.service.SchService009;
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

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController009 {
    // service , mapper , schAuthorityHelper 선언
    private final SchService009 schService009;
    private final SchMapper009 schMapper009;
    private final SchAuthorityHelper schAuthorityHelper;

    // 나의 캘린더 목록 조회 API
    @RequestMapping(value = "/sch009", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch009(@RequestBody @Valid SchRqDto009 schRqDto009) throws Exception {
        log.info("sch009 API START !!!");
        log.info("sch009 REQUEST DATA : " + schRqDto009);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto009);
        log.info("sch009 validApiRequest Success !!! ");
        // API 권한 검사 메소드 호출
        validApiAuthority(schRqDto009);
        log.info("sch009 validApiAuthority Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch009 API SUCCESS");
        schResponse.setResponseData(schService009.findMyCalendarsInfo(schRqDto009));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch009 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch009 RESPONSE DATA : " + schResponse);
        log.info("sch009 API END !!!");

        // return
        return schResponse;
    }

    // sch009 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto009 schRqDto009) throws Exception {
        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper009.selectUserCount(schRqDto009);
        if (selectUserCount == 0) {
            log.error("$$$ sch009 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch009 validApiRequest fail !!! (schRqDto009 : " + schRqDto009 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
    }

    // sch009 API 권한 검증 진행
    private void validApiAuthority(SchRqDto009 schRqDto009) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        SchRqDto999 schRqDto999 = modelMapper.map(schRqDto009, SchRqDto999.class);
        schRqDto999.setModuleApiType(SchConstValue.CALENDAR_TYPE);
        schRqDto999.setModuleApiPersonality(SchConstValue.FIND_PERSONALITY);
        boolean authority = schAuthorityHelper.confirmAuthorityInfo(schRqDto999);
        if (!authority) {
            log.error("$$$ sch009 validApiAuthority fail !!! (NotAuthorizedForCalFindException) $$$");
            log.error("$$$ sch009 validApiAuthority fail !!! (schRqDto009 : " + schRqDto009 + ") $$$");
            throw new ExceptionCustom.NotAuthorizedForCalFindException();
        }
    }
}
