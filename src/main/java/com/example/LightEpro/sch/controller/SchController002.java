package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper002;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService002;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class SchController002 {
    // service , mapper 선언
    private final SchService002 schService002;
    private final SchMapper002 schMapper002;

    // 단일 일정 수정 API
    @RequestMapping(value = "/sch002", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch002(@RequestBody @Valid SchRqDto002 schRqDto002) throws Exception {
        log.info("sch002 API START !!!");
        log.info("sch002 REQUEST DATA : " + schRqDto002);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        // stopWatch 시작
        stopWatch.start();

        // 유효성 검사 메소드 호출
        validApiRequest(schRqDto002);
        log.info("sch002 validApiRequest Success !!! ");

        // SchResponse 객체 데이터 생성 및 할당
        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch002 API SUCCESS");
        schResponse.setResponseData(schService002.modifyScheduleInfo(schRqDto002));

        // stopWatch 종료
        stopWatch.stop();

        log.info("sch002 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch002 RESPONSE DATA : " + schResponse);
        log.info("sch002 API END !!!");

        // return
        return schResponse;
    }

    // sch002 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto002 schRqDto002) throws Exception {
        // schRqDto002 객체 데이터 추출
        SchRqDto002.Schedule schedule = schRqDto002.getSchedule();
        List<SchRqDto002.Participant> participants = schRqDto002.getParticipants();
        List<SchRqDto002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();

        // 요청값으로 받은 시작일자 값과 , 종료일자 값을 추출한다.
        LocalDateTime startDate = schedule.getStartDate();
        LocalDateTime endDate = schedule.getEndDate();

        // 시작일자 값이 , 종료일자보다 큰 경우 Exception 처리
        if (startDate.isAfter(endDate)) {
            log.error("$$$ sch002 validApiRequest fail !!! (NotValidSchStartEndDateException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new ExceptionCustom.NotValidSchStartEndDateException();
        }
        // 요청값으로 받은 캘린더 시퀀스 값을통해 , 캘린더 타입을 조회한다.
        String selectCalendarTypeValue = schMapper002.selectCalendarType(schRqDto002);
        // 캘린더 타입의 값이 빈 값인 경우 Exception 처리
        if (selectCalendarTypeValue.equals(SchConstValue.EMPTY_VALUE)) {
            log.error("$$$ sch002 validApiRequest fail !!! (NotFoundCalException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new ExceptionCustom.NotFoundCalException();
        }
        // 개인캘린더에 포함된 일정 수정시에 , 공개범위 데이터가 포함되는 경우 Exception 처리
        if (selectCalendarTypeValue.equals(SchConstValue.ECAL_TYPE) && (disclosureScopes != null)) {
            log.error("$$$ sch002 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new ExceptionCustom.IncorrectIncludException();
        }
        // 일정 수정 진행 전 , 요청값으로 받은 일정 시퀀스값을 통해 일정이 존재하는지 판단 후 , 존재하지 않는다면 Exception 처리
        int selectScheduleCountValue = schMapper002.selectScheduleCount(schRqDto002);
        if (selectScheduleCountValue == 0) {
            log.error("$$$ sch002 validApiRequest fail !!! (NotFoundSchException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new ExceptionCustom.NotFoundSchException();
        }
        // 요청값의 참여자 데이터 중 기존 일정의 등록자 값이 존재하지 않는 경우 Exception 처리
        int selectOriginRegistrantValue = schMapper002.selectOriginRegistrant(schRqDto002);
        boolean existRegistrant = participants.stream().anyMatch(participant ->
                (participant.getCdeSeq() == selectOriginRegistrantValue)
                        && participant.getCdeType().equals(SchConstValue.CDE_E_TYPE));
        if (existRegistrant == false) {
            log.error("$$$ sch002 validApiRequest fail !!! (NotFoundRegistrantException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new ExceptionCustom.NotFoundRegistrantException();
        }
    }
}
