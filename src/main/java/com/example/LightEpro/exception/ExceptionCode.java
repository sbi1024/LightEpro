package com.example.LightEpro.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    /**
     * common server exception
     */
    INTERNAL_SERVER_EXCEPTION("FAIL", 500, "INTERNAL_SERVER_EXCEPTION", null),
    NOT_INCLUDED_REQUIRED_VALUE_EXCEPTION("FAIL", 1001, "NOT_INCLUDED_REQUIRED_VALUE_EXCEPTION", null),
    /**
     * custom server exception
     */
    NOT_FOUND_SCH_EXCEPTION("FAIL", 1002, "NOT_FOUND_SCH_EXCEPTION", null),
    NOT_BE_GREATER_THAN_END_DATE_EXCEPTION("FAIL", 1003, "NOT_BE_GREATER_THAN_END_DATE_EXCEPTION", null),
    NOT_BE_INCLUDED_DISCLOSURE_EXCEPTION("FAIL", 1004, "NOT_BE_INCLUDED_DISCLOSURE_EXCEPTION", null),
    NOT_INCLUDED_CALENDAR_SEQUENCES_EXCEPTION("FAIL", 1005, "NOT_INCLUDED_CALENDAR_SEQUENCES_EXCEPTION", null),
    NOT_FOUND_CAL_EXCEPTION("FAIL", 1006, "NOT_FOUND_CAL_EXCEPTION", null),
    NOT_FOUND_REGISTRANT_EXCEPTION("FAIL", 1007, "NOT_FOUND_REGISTRANT_EXCEPTION", null),
    NOT_FOUND_OWNER_EXCEPTION("FAIL", 1008, "NOT_FOUND_OWNER_EXCEPTION", null),
    NOT_BE_INCLUDED_MANAGER_EXCEPTION("FAIL", 1009, "NOT_BE_INCLUDED_MANAGER_EXCEPTION", null),
    NOT_FOUND_USER_EXCEPTION("FAIL", 1010, "NOT_FOUND_USER_EXCEPTION", null);

    private final String exceptionStatus;
    private final int exceptionCode;
    private final String exceptionMsg;
    private Object exceptionData;

    ExceptionCode(String exceptionStatus, int exceptionCode, String exceptionMsg, Object exceptionData) {
        this.exceptionStatus = exceptionStatus;
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
        this.exceptionData = exceptionData;
    }

    public void setExceptionData(Object exceptionData) {
        this.exceptionData = exceptionData;
    }
}
