package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.exception.SchExceptionCustom;
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

    private final SchService002 schService002;
    private final SchMapper002 schMapper002;

    // 단일 일정 수정 API
    @RequestMapping(value = "/sch002", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch002(@RequestBody @Valid SchRqDto002 schRqDto002) throws Exception{
        log.info("sch002 API START !!!");
        log.info("sch002 REQUEST DATA : " + schRqDto002);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto002);
        log.info("sch002 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setResponseCode(200);
        schResponse.setResponseMsg("sch002 API SUCCESS");
        schResponse.setResponseData(schService002.modifySingleSch(schRqDto002));

        stopWatch.stop();

        log.info("sch002 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch002 RESPONSE DATA : " + schResponse);
        log.info("sch002 API END !!!");

        return schResponse;
    }

    // sch002 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    // TODO 일정 수정 진행시에 , 등록자 정보가 변경되는지 확인
    public void validApiRequest(SchRqDto002 schRqDto002) throws Exception {
        SchRqDto002.Sch sch = schRqDto002.getSch();
        SchRqDto002.Calendar calendar = schRqDto002.getCalendar();
        List<SchRqDto002.Participant> participants = schRqDto002.getParticipants();
        List<SchRqDto002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();

        // 요청값으로 받은 시작일자 값과 , 종료일자 값을 추출한다.
        LocalDateTime startDate = sch.getStartDate();
        LocalDateTime endDate = sch.getEndDate();

        // 시작일자 값이 , 종료일자보다 큰 경우 Exception 처리
        if (startDate.isAfter(endDate)) {
            log.error("$$$ sch002 validApiRequest fail !!! (NotValidSchStartEndDateException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new SchExceptionCustom.NotValidSchStartEndDateException();
        }
        // 개인캘린더 수정시에 , 참여자 혹은 공개범위 데이터가 포함되는 경우 Exception 처리
        String calType = schMapper002.checkCalType(calendar.getCalSeq());
        if (calType.equals(SchConstValue.ECAL_TYPE) && (participants != null || disclosureScopes != null)) {
            log.error("$$$ sch002 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new SchExceptionCustom.IncorrectIncludException();
        }
        // 일정 수정 진행 전 , 요청값으로 받은 일정 시퀀스값을 통해 일정이 존재하는지 판단 후 , 존재하지 않는다면 Exception 처리
        int schCnt = schMapper002.checkSchExist(schRqDto002);
        if (schCnt == 0) {
            log.error("$$$ sch002 validApiRequest fail !!! (NotFoundSchException) $$$");
            log.error("$$$ sch002 validApiRequest fail !!! (schRqDto002 : " + schRqDto002 + ") $$$");
            throw new SchExceptionCustom.NotFoundSchException();
        }
    }
}
