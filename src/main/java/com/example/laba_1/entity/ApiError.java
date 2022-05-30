package com.example.laba_1.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private String message;
    private String debugMessage;
    private HttpStatus status;

    public ApiError(String message, String debugMessage, HttpStatus status) {
        this.message = message;
        this.debugMessage = debugMessage;
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}