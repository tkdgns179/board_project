package com.example.board_project.web.dto;

import lombok.Getter;

@Getter
public enum ErrorCode {

    AUTHENTICATION_ERROR(400, "C001", "invalid authentication");

    private int status;
    private String code;
    private String message;
    private String detail;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
