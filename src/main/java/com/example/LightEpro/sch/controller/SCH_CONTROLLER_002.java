package com.example.LightEpro.sch.controller;

import com.example.LightEpro.sch.constant.CONST_VALUE;
import com.example.LightEpro.sch.dto.sch_000.SCH_RQ_DTO_000;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_000;
import com.example.LightEpro.sch.response.SCH_RESPONSE;
import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.service.SCH_SERVICE_002;
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
public class SCH_CONTROLLER_002 {

    private final SCH_SERVICE_002 schService002;
    private final SCH_MAPPER_000 schMapper000;

    // 단일 일정 수정 API
    @RequestMapping(value = "/SCH_002", method = {RequestMethod.GET, RequestMethod.POST})
    public SCH_RESPONSE SCH_002(@RequestBody @Valid SCH_RQ_DTO_002 schRqDto002) throws Exception{
        log.info("SCH_002 API START !!!");
        log.info("SCH_002 REQUEST DATA : " + schRqDto002);

        validApiRequest(schRqDto002);

        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        schResponse.setResponseStatus("SUCCESS");
        schResponse.setReponseCode(200);
        schResponse.setResponseMsg("SCH_002 API SUCCESS");
        schResponse.setResponseData(schService002.modifySingleSch(schRqDto002));

        log.info("SCH_002 RESPONSE DATA : " + schResponse);
        log.info("SCH_002 API END !!!");

        return schResponse;
    }

    // SCH_000 API 요청값 중 필요한 추가적 객체 데이터 재 검증 진행
    public void validApiRequest(SCH_RQ_DTO_002 schRqDto002) throws Exception {
        SCH_RQ_DTO_002.Sch sch = schRqDto002.getSch();
        List<SCH_RQ_DTO_002.Participant> participants = schRqDto002.getParticipants();
        List<SCH_RQ_DTO_002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();

        LocalDateTime startDate = sch.getStartDate();
        LocalDateTime endDate = sch.getEndDate();

        // TODO 수정 예정_3
        if (startDate.isAfter(endDate)) {
            throw new Exception("일정의 시작일자가 , 종료일자보다 큽니다.");
        }
        // TODO 수정 예정_4
        // 개인캘린더 등록시 요청값에 참여자 및 공개범위 데이터가 포함되어 있는 경우 강제 Exception 발생
        String calType = schMapper000.checkCalType(sch.getCalSeq());
        if (calType.equals(CONST_VALUE.ECAL_TYPE) && (participants != null || disclosureScopes != null)) {
            throw new Exception("개인 캘린더 일정 등록시에는 , 참여자 및 공개범위 데이터가 포함되지 않습니다.");
        }
    }
}
