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

        String calType = schRqDto005.getCalender().getCalType();
        SchRqDto005.Owner owner = schRqDto005.getOwner();
        List<SchRqDto005.Manager> managers = schRqDto005.getManagers();

        // 1. 개인캘린더 등록시에 , 관리자 요청값이 포함되어 들어오는 경우 Exception
        if (calType.equals(ConstValue.ECAL_TYPE) && managers != null) {
            log.error("$$$ sch005 validApiRequest fail !!! (IncorrectIncludException) $$$");
            log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
            throw new ExceptionCustom.IncorrectIncludException();
        }

        // 2. 캘린더 등록시에 , 캘린더의 소유자 정보가 , 관리자 정보에도 포함이 되어 있다면 Exception
        // 사유 : 소유자에 포함되어 있는 인원이 , 관리자에도 포함될수 없다 (소유자와 , 관리자의 권한이 다르기 떄문)
        int ownerCdeSeq = owner.getCdeSeq();
        String ownerCdeType = owner.getCdeType();
        for (SchRqDto005.Manager manager : managers) {
            int manageCdeSeq = manager.getCdeSeq();
            String manageCdeType = manager.getCdeType();
            if (ownerCdeSeq == manageCdeSeq && ownerCdeType.equals(manageCdeType)) {
                log.error("$$$ sch005 validApiRequest fail !!! (IncorrectIncludException) $$$");
                log.error("$$$ sch005 validApiRequest fail !!! (schRqDto005 : " + schRqDto005 + ") $$$");
                throw new ExceptionCustom.IncorrectIncludException();
            }
        }

    }
}
