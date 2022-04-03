package com.example.board_project.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException {

    private static final long serialVersionUID = 5988202995417040949L;

    private String message;
    private Map<String, String> errorMap;

    public CustomValidationApiException(String message) {
        super(message);
        this.message = message;
    }

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        this.message = message;
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() { return errorMap; }

}

