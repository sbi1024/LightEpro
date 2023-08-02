package com.example.LightEpro.sch.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    /**
     * common server exception
     */
    INTERNAL_SERVER_EXCEPTION("FAIL", 500, "INTERNAL_SERVER_EXCEPTION", null),
    VALID_REQUEST_EXCEPTION("FAIL", 1001, "VALID_REQUEST_EXCEPTION", null),

    /**
     * custom server exception
     */
    NOT_FOUNT_SCH_EXCEPTION("FAIL", 1002, "NOT_FOUNT_SCH_EXCEPTION", null),
    NOT_VALID_DATE_EXCEPTION("FAIL", 1003, "NOT_VALID_DATE_EXCEPTION", null),
    INCORRECT_INCLUDE_EXCEPTION("FAIL", 1004, "INCOLLECT_INCLUDE_EXCEPTION", null);

    private final String exceptionStatus;
    private final int exceptionCode;
    private String exceptionMsg;
    private Object exceptionData;

    ExceptionCode(String exceptionStatus, int exceptionCode, String exceptionMsg, Object exceptionData) {
        this.exceptionStatus = exceptionStatus;
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
        this.exceptionData = exceptionData;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public void setExceptionData(Object exceptionData) {
        this.exceptionData = exceptionData;
    }
}
