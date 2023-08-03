package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.ConstValue;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper002;
import com.example.LightEpro.sch.response.SchResponse;
import com.example.LightEpro.sch.service.SchService002;
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
public class SchController002 {

    private final SchService002 schService002;
    private final SchMapper002 schMapper002;

    // 단일 일정 수정 API
    @RequestMapping(value = "/sch002", method = {RequestMethod.GET, RequestMethod.POST})
    public SchResponse sch002(@RequestBody @Valid SchRqDto002 schRqDto002) throws Exception{
        log.info("sch002 API START !!!");
        log.info("sch002 REQUEST DATA : " + schRqDto002);

        validApiRequest(schRqDto002);

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("sch002 API SUCCESS");
        schResponse.setResponseData(schService002.modifySingleSch(schRqDto002));

        log.info("sch002 RESPONSE DATA : " + schResponse);
        log.info("sch002 API END !!!");

        return schResponse;
    }

    // SCH_002 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
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
            throw new ExceptionCustom.NotValidSchStartEndDateException();
        }
        // 개인캘린더 수정시에 , 참여자 혹은 공개범위 데이터가 포함되는 경우 Exception 발생
        String calType = schMapper002.checkCalType(calendar.getCalSeq());
        if (calType.equals(ConstValue.ECAL_TYPE) && (participants != null || disclosureScopes != null)) {
            throw new ExceptionCustom.IncorrectIncludException();
        }
    }
}
