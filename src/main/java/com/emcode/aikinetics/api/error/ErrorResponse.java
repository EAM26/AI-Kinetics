package com.emcode.aikinetics.api.error;

public  class ErrorResponse {
    public final String code;
    public final String message;
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
