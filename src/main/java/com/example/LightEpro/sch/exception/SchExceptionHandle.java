package com.example.LightEpro.sch.exception;


import com.example.LightEpro.sch.response.SchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class SchExceptionHandle extends RuntimeException {
    /**
     * Exception
     */
    @ExceptionHandler(Exception.class)
    public SchResponse handleException(Exception e) {
        SchExceptionCode errorCode = SchExceptionCode.INTERNAL_SERVER_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ Exception !!! (Exception) $$$");
        log.error("$$$ handleException !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }


    /**
     * 유효성 검증 실패시 발생 Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SchResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        SchExceptionCode errorCode = SchExceptionCode.VALID_REQUEST_EXCEPTION;
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        errorCode.setExceptionData(String.valueOf(allErrors));

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ MethodArgumentNotValidException !!! (Exception) $$$");
        log.error("$$$ handleMethodArgumentNotValidException !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }


    /**
     * 일정 조회시 , 디비상 없는 일정인 경우 발생 Exception
     */
    @ExceptionHandler(SchExceptionCustom.NotFoundSchException.class)
    public SchResponse NotFountSchException(SchExceptionCustom.NotFoundSchException e) {
        SchExceptionCode errorCode = SchExceptionCode.NOT_FOUND_SCH_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ ExceptionCustom.NotFountSchException !!! (Exception) $$$");
        log.error("$$$ NotFountSchException !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 시작일자 값이 종료일자 값보다 큰 경우 발생 Exception
     */
    @ExceptionHandler(SchExceptionCustom.NotValidSchStartEndDateException.class)
    public SchResponse NotValidSchStartEndDateException(SchExceptionCustom.NotValidSchStartEndDateException e) {
        SchExceptionCode errorCode = SchExceptionCode.NOT_VALID_DATE_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ ExceptionCustom.NotValidSchStartEndDateException !!! (Exception) $$$");
        log.error("$$$ NotValidSchStartEndDateException !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 개인 캘린더에 일정등록시 , 참여자 혹은 공개범위 데이터가 포함된 요청값이 들어오는 경우 발생 Exception
     */
    @ExceptionHandler(SchExceptionCustom.IncorrectIncludException.class)
    public SchResponse IncorrectIncludException(SchExceptionCustom.IncorrectIncludException e) {
        SchExceptionCode errorCode = SchExceptionCode.INCORRECT_INCLUDE_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ ExceptionCustom.IncorrectIncludException !!! (Exception) $$$");
        log.error("$$$ IncorrectIncludException !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    /**
     * 캘린더 시퀀스 값이 유효하지 않는 경우 (요청값의 캘린더 리스트 사이즈가 0 이거나 null 인 경우)
     */
    @ExceptionHandler(SchExceptionCustom.NotValidCalSeqsException.class)
    public SchResponse NotValidCalSeqsException(SchExceptionCustom.NotValidCalSeqsException e) {
        SchExceptionCode errorCode = SchExceptionCode.NOT_VALID_CAL_SEQS_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ ExceptionCustom.NotValidCalSeqsException !!! (Exception) $$$");
        log.error("$$$ NotValidCalSeqsException !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }
    /**
     * 캘린더 조회시 , 디비상 없는 캘린더인 경우 발생 Exception
     */
    @ExceptionHandler(SchExceptionCustom.NotFoundCalException.class)
    public SchResponse NotFoundCalException(SchExceptionCustom.NotFoundCalException e) {
        SchExceptionCode errorCode = SchExceptionCode.NOT_FOUND_CAL_EXCEPTION;
        errorCode.setExceptionData(e.getMessage());

        SchResponse schResponse = new SchResponse();
        schResponse.setResponseStatus(errorCode.getExceptionStatus());
        schResponse.setResponseCode(errorCode.getExceptionCode());
        schResponse.setResponseMsg(errorCode.getExceptionMsg());
        schResponse.setResponseData(errorCode.getExceptionData());

        log.error("$$$ ExceptionCustom.NotFoundCalException !!! (Exception) $$$");
        log.error("$$$ NotFoundCalException !!! (schResponse : " + schResponse + ") $$$");

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }
}
