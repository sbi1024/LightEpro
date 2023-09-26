package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.dto.sch999.SchRqDto999;
import com.example.LightEpro.sch.helper.SchAuthorityHelper;
import com.example.LightEpro.sch.mapper.SchMapper005;
import com.example.LightEpro.sch.mapper.SchMapper999;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService005;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class SchController005 {
    // service , mapper 선언
    private final SchService005 schService005;
    private final SchMapper005 schMapper005;
    private final SchAuthorityHelper schAuthorityHelper;

    // 캘린더 등록 API
    @RequestMapping(value = "/sch005", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch005(@RequestBody @Valid SchRqDto005 schRqDto005) throws Exception {
        log.info("sch005 API START !!!");
        log.info("sch005 REQUEST DATA : " + schRqDto005);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto005);
        log.info("sch005 validApiRequest Success !!! ");
        // API 권한 검사 메소드 호출
        validApiAuthority(schRqDto005);
        log.info("sch005 validApiAuthority Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch005 API SUCCESS");
        schResponse.setResponseData(schService005.createCalendarInfo(schRqDto005));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch005 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch005 RESPONSE DATA : " + schResponse);
        log.info("sch005 API END !!!");

        // return
        return schResponse;
    }


    // sch005 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto005 schRqDto005) throws Exception {
        // schRqDto005 객체 데이터 추출
        SchRqDto005.User user = schRqDto005.getUser();
        SchRqDto005.Calendar calender = schRqDto005.getCalendar();
        SchRqDto005.Owner owner = schRqDto005.getOwner();
        List<SchRqDto005.Manager> managers = schRqDto005.getManagers();

        // 요청값으로 받은 user 객체의 데이터가 조직도 시스템에서 존재하지 않는 인원인 경우 Exception 처리
        int selectUserCount = schMapper005.selectUserCount(schRqDto005);
        if (selectUserCount == 0) {
            log.error("$$$ sch005 validApiRequest fail !!! (NotFoundUserException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.NotFoundUserException();
        }
        // 캘린더 등록시에 , 캘린더의 소유자 cdeSeq 값이 요청자의 userSeq 일치하지 않거나 , 소유자의 타입이 E가 아닌 경우 Exception 처리
        if ((user.getUserSeq() != owner.getCdeSeq())
                || !owner.getCdeType().equals(SchConstValue.CDE_I_TYPE)) {
            log.error("$$$ sch005 validApiRequest fail !!! (NotFoundOwnerException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.NotFoundOwnerException();
        }
        // 개인캘린더 등록시에 , 관리자 요청값이 포함되어 들어오는 경우 Exception 처리
        if (calender.getCalType().equals(SchConstValue.CAL_I_TYPE) && (managers != null)) {
            log.error("$$$ sch005 validApiRequest fail !!! (NotBeIncludedManagerException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.NotBeIncludedManagerException();
        }
    }

    private void validApiAuthority(SchRqDto005 schRqDto005) throws Exception {
        SchRqDto005.User user = schRqDto005.getUser();
        SchRqDto005.Calendar calendar = schRqDto005.getCalendar();

        SchRqDto999.User requestUser = new SchRqDto999.User();
        requestUser.setUserCompSeq(user.getUserCompSeq());
        requestUser.setUserDeptSeq(user.getUserDeptSeq());
        requestUser.setUserSeq(user.getUserSeq());

        SchRqDto999.Calendar requestCalendar = new SchRqDto999.Calendar();
        requestCalendar.setCalSeq(calendar.getCalSeq());

        SchRqDto999 schRqDto999 = SchRqDto999.builder()
                .user(requestUser)
                .calendar(requestCalendar)
                .moduleApiType(SchConstValue.CALENDAR_TYPE)
                .moduleApiPersonality(SchConstValue.CREATE_PERSONALITY)
                .build();

        boolean AuthorityDetermine = schAuthorityHelper.confirmAuthorityInfo(schRqDto999);

        if (AuthorityDetermine == false) {
            throw new Exception("권한이 존재하지 않습니다.");
        }
    }
}
