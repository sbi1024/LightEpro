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
    @RequestMapping(value = "/SCH_000", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse SCH_000(@RequestBody @Valid SchRqDto000 schRqDto000) throws Exception {
        log.info("SCH_000 API START !!!");
        log.info("SCH_000 REQUEST DATA : " + schRqDto000);

        validApiRequest(schRqDto000);

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("SCH_000 API SUCCESS");
        schResponse.setResponseData(schService000.createSingleSch(schRqDto000));

        log.info("SCH_000 RESPONSE DATA : " + schResponse);
        log.info("SCH_000 API END !!!");

        return schResponse;
    }

    // SCH_000 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SchRqDto000 schRqDto000) throws Exception {
        SchRqDto000.Sch sch = schRqDto000.getSch();
        List<SchRqDto000.Participant> participants = schRqDto000.getParticipants();
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();

        // 요청값으로 받은 시작일자 값과 , 종료일자 값을 추출한다.
        LocalDateTime startDate = sch.getStartDate();
        LocalDateTime endDate = sch.getEndDate();

        // 시작일자 값이 , 종료일자보다 큰 경우 Exception 처리
        if (startDate.isAfter(endDate)) {
            throw new ExceptionCustom.NotValidSchStartEndDateException();
        }
        // 개인캘린더 등록시에 , 참여자 혹은 공개범위 데이터가 포함되는 경우 Exception 발생
        String calType = schMapper000.checkCalType(sch.getCalSeq());
        if (calType.equals(ConstValue.ECAL_TYPE) && (participants != null || disclosureScopes != null)) {
            throw new ExceptionCustom.IncorrectIncludException();
        }
    }
}
