package com.example.LightEpro.sch.exception;

import lombok.Getter;

@Getter
public enum ERROR_CODE {
    INTERNAL_SERVER_ERROR(500, "SCH-001", "INTERNAL_SERVER ERROR");

    private final int status;
    private final String code;
    private final String description;

    ERROR_CODE(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }
}
