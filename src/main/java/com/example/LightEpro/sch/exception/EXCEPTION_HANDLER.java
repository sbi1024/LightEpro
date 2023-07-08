package com.example.LightEpro.sch.exception;

import com.example.LightEpro.sch.response.SCH_RESPONSE;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class EXCEPTION_HANDLER extends RuntimeException {
    // Exception 일괄 처리 메소드
    @ExceptionHandler(Exception.class)
    public SCH_RESPONSE handleException(Exception e) {
        EXCEPTION_COLLECT errorCode = EXCEPTION_COLLECT.INTERNAL_SERVER_ERROR;
        errorCode.setErrorData(e.getMessage());

        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        schResponse.setResponseData(errorCode.getErrorData());
        schResponse.setResponseMsg(errorCode.getErrorMsg());
        schResponse.setReponseCode(errorCode.getErrorCode());

        e.printStackTrace();
        e.getMessage();

        return schResponse;
    }

    // valid 관련 Exception 일괄처리 메소드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SCH_RESPONSE handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        EXCEPTION_COLLECT errorCode = EXCEPTION_COLLECT.VALID_SERVER_ERROR;
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        errorCode.setErrorMsg(String.valueOf(allErrors));

        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        schResponse.setResponseData(errorCode.getErrorData());
        schResponse.setResponseMsg(errorCode.getErrorMsg());
        schResponse.setReponseCode(errorCode.getErrorCode());

        return schResponse;
    }



}
