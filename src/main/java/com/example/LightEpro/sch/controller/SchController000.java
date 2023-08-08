package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.ConstValue;
import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper000;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService000;
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
public class SchController000 {

    private final SchService000 schService000;
    private final SchMapper000 schMapper000;

    // 단일 일정 등록 API
    @RequestMapping(value = "/sch000", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch000(@RequestBody @Valid SchRqDto000 schRqDto000) throws Exception {
        log.info("sch000 API Start !!!");
        log.info("sch000 Request Data : " + schRqDto000);

        // API 실행시간 체크를 위한 stopWatch 객체 생성
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        validApiRequest(schRqDto000);
        log.info("sch000 validApiRequest Success !!! ");

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch000 API SUCCESS");
        schResponse.setResponseData(schService000.createSingleSch(schRqDto000));

        stopWatch.stop();

        log.info("sch000 API runTime : {}", stopWatch.getTotalTimeSeconds());
        log.info("sch000 Response Data : " + schResponse);
        log.info("sch000 API End !!!");

        return schResponse;
    }

    // sch000 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto000 schRqDto000) throws Exception {
        SchRqDto000.Sch sch = schRqDto000.getSch();
        SchRqDto000.Calendar calendar = schRqDto000.getCalendar();
        List<SchRqDto000.Participant> participants = schRqDto000.getParticipants();
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();

        // 요청값으로 받은 시작일자 값과 , 종료일자 값을 추출한다.
        LocalDateTime startDate = sch.getStartDate();
        LocalDateTime endDate = sch.getEndDate();

        // 시작일자 값이 , 종료일자보다 큰 경우 Exception 처리
        if (startDate.isAfter(endDate)) {
            log.error("$$$ sch000 validApiRequest fail !!! (NotValidSchStartEndDateException) $$$");
            log.error("$$$ sch000 validApiRequest fail !!! (schRqDto000 : " + schRqDto000 + ") $$$");
            throw new ExceptionCustom.NotValidSchStartEndDateException();
        }
        // 개인캘린더 등록시에 , 참여자 혹은 공개범위 데이터가 포함되는 경우 Exception 처리
        String calType = schMapper000.checkCalType(calendar.getCalSeq());
        if (calType.equals(ConstValue.ECAL_TYPE) && (participants != null || disclosureScopes != null)) {
            log.error("$$$ sch000 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch000 validApiRequest fail !!! (schRqDto000 : " + schRqDto000 + ") $$$");
            throw new ExceptionCustom.IncorrectIncludException();
        }
    }
    // TODO 주석 및 로그 활성화 먼저 진행 !!!!!!
    // TODO 일정 단일 수정 / 캘린더 단일 수정 건 재 검증 및 확인 필요 
    
    // TODO 조직도 회사 / 부서 / 사원 타입에 대한 정리 필요
    // TODO 관리자 / 소유자 / 참여자 / 공개범위 인원에 대한 조회조건 확립 필요
    // TODO 권한체크 후 서비스 로직 실행 건에 대한 확인 필요
    // TODO > 구성해야할 API 목록
    // 1. 캘린더 조회
    // 2. 캘린더 삭제
    // 3. 캘린더 수정
    // 4. 캘린더 목록 조회 (내)
    // 이후 권한 관련 이슈 체크 및 부서 및 회사 관련 처리를 위한 EMP 쪽 작업 진행
    // EMP 완료되는 시점에서 프론트작업 진행 필요
    // 반복일정 어떤식으로 정책확립할지 확인 필요
}
