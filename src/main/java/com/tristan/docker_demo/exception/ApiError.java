package com.tristan.docker_demo.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApiError {

    private LocalDate timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    
    public ApiError(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now().toLocalDate();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

}
