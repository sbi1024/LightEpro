package com.example.LightEpro.sch.exception;

import com.example.LightEpro.sch.response.SCH_RESPONSE;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class EXCEPTION_HANDLER extends RuntimeException {
    // valid 관련 Exception 일괄처리 메소드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SCH_RESPONSE handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        schResponse.setResponseData(errors);

        return schResponse;
        // return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> dataExceptionHandle() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> sqlExceptionHandle() {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }

    //    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<SCH_ERROR_RESPONSE> handleException(Exception e) {
//        ERROR_CODE errorCode = ERROR_CODE.INTERNAL_SERVER_ERROR;
//        SCH_ERROR_RESPONSE response =  new SCH_ERROR_RESPONSE(errorCode.getCode(), e.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
//    }


    @ExceptionHandler(Exception.class)
    public SCH_RESPONSE handleException(Exception e) {
        ERROR_CODE errorCode = ERROR_CODE.INTERNAL_SERVER_ERROR;

        SCH_RESPONSE schResponse = new SCH_RESPONSE();
        schResponse.setResponseData(errorCode);
        schResponse.setResponseMsg(e.getMessage());
        schResponse.setReponseCode(errorCode.getCode());

        return schResponse;
    }

}
