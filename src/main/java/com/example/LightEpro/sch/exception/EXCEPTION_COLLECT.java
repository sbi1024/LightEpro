package com.example.LightEpro.sch.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum EXCEPTION_COLLECT {
    INTERNAL_SERVER_ERROR("FAIL", 500, "INTERNAL_SERVER ERROR", null),
    VALID_SERVER_ERROR("FAIL", 1000, "VALID_SERVER_ERROR", null);

    private final String errorStatus;
    private final int errorCode;
    private String errorMsg;
    private Object errorData;

    EXCEPTION_COLLECT(String errorStatus, int errorCode, String errorMsg, Object errorData) {
        this.errorStatus = errorStatus;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorData = errorData;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }
}
