package com.example.LightEpro.exception;


import com.example.LightEpro.sch.response.SchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle extends RuntimeException {
    /**
     * 최상위 Exception
     */
    @ExceptionHandler(Exception.class)
    public SchResponse InternalServerException(Exception e) {
        ExceptionCode errorCode = ExceptionCode.INTERNAL_SERVER_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ InternalServerException !!! (Exception) $$$");
        log.error("$$$ INTERNAL_SERVER_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }


    /**
     * DTO 클래스의 요청값 유효성 검증 실패시 발생 Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SchResponse NotIncludedRequiredValueException(MethodArgumentNotValidException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_INCLUDED_REQUIRED_VALUE_EXCEPTION;
        Map<String, String> exceptionData = new HashMap<>();
        errorCode.setExceptionData(exceptionData);
        e.getBindingResult().getAllErrors().forEach(allError -> exceptionData.put(((FieldError) allError).getField(), allError.getDefaultMessage()));

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotIncludedRequiredValueException !!! (Exception) $$$");
        log.error("$$$ NOT_INCLUDED_REQUIRED_VALUE_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }


    /**
     * 시스템에 존재하지 않는 일정인 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotFoundSchException.class)
    public SchResponse NotFountSchException(ExceptionCustom.NotFoundSchException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_FOUND_SCH_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotFountSchException !!! (Exception) $$$");
        log.error("$$$ NOT_FOUND_SCH_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 시작일자 값이 종료일자 값보다 큰 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotBeGreaterThanEndDateException.class)
    public SchResponse NotBeGreaterThanEndDateException(ExceptionCustom.NotBeGreaterThanEndDateException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_BE_GREATER_THAN_END_DATE_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotBeGreaterThanEndDateException !!! (Exception) $$$");
        log.error("$$$ NOT_BE_GREATER_THAN_END_DATE_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 개인 캘린더에 일정 등록/수정시 , 공개범위 데이터가 포함된 요청값이 들어오는 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotBeIncludedDisclosureException.class)
    public SchResponse NotBeIncludedDisclosureException(ExceptionCustom.NotBeIncludedDisclosureException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_BE_INCLUDED_DISCLOSURE_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotBeIncludedDisclosureException !!! (Exception) $$$");
        log.error("$$$ NOT_BE_INCLUDED_DISCLOSURE_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 캘린더 시퀀스 값이 유효하지 않는 경우 (요청값의 캘린더 리스트 사이즈가 0 이거나 null 인 경우)
     */
    @ExceptionHandler(ExceptionCustom.NotIncludedCalendarSequencesException.class)
    public SchResponse NotIncludedCalendarSequencesException(ExceptionCustom.NotIncludedCalendarSequencesException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_INCLUDED_CALENDAR_SEQUENCES_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotIncludedCalendarSequencesException !!! (Exception) $$$");
        log.error("$$$ NOT_INCLUDED_CALENDAR_SEQUENCES_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 캘린더 조회시 , 디비상 없는 캘린더인 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotFoundCalException.class)
    public SchResponse NotFoundCalException(ExceptionCustom.NotFoundCalException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_FOUND_CAL_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotFoundCalException !!! (Exception) $$$");
        log.error("$$$ NOT_FOUND_CAL_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 일정 수정 진행 시 , 요청값의 참여자 데이터 중 , 기존 일정의 등록자 정보가 미포함 되는 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotFoundRegistrantException.class)
    public SchResponse NotFoundRegistrantException(ExceptionCustom.NotFoundRegistrantException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_FOUND_REGISTRANT_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotFoundRegistrantException !!! (Exception) $$$");
        log.error("$$$ NOT_FOUND_REGISTRANT_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 캘린더 수정 진행 시 , 요청값의 소유자 데이터에 , 기존 캘린더 소유자 정보가 미포함 되는 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotFoundOwnerException.class)
    public SchResponse NotFoundOwnerException(ExceptionCustom.NotFoundOwnerException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_FOUND_OWNER_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotFoundOwnerException !!! (Exception) $$$");
        log.error("$$$ NOT_FOUND_OWNER_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 개인 캘린더 등록/수정 진행시 , 공개범위 데이터가 포함된 요청값이 들어오는 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotBeIncludedManagerException.class)
    public SchResponse NotBeIncludedManagerException(ExceptionCustom.NotBeIncludedManagerException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_BE_INCLUDED_MANAGER_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotBeIncludedManagerException !!! (Exception) $$$");
        log.error("$$$ NOT_BE_INCLUDED_MANAGER_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 조직도 시스템에서 존재하지 않는 사용자인 경우 발생 Exception
     */
    @ExceptionHandler(ExceptionCustom.NotFoundUserException.class)
    public SchResponse NotFoundUserException(ExceptionCustom.NotFoundUserException e) {
        ExceptionCode errorCode = ExceptionCode.NOT_FOUND_USER_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ NotFoundUserException !!! (Exception) $$$");
        log.error("$$$ NOT_FOUND_USER_EXCEPTION !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }
}
