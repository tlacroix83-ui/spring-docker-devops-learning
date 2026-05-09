package com.tristan.docker_demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().map(error -> error.getField() + " " + error.getDefaultMessage()).orElse("Validation failed");

        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("[handleValidationExceptions] Validation failed: {}", errorMessage);
        }

        return new ApiError(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), errorMessage, request.getRequestURI());
    }

    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleTodoNotFoundException(TodoNotFoundException ex, HttpServletRequest request) {
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("[handleTodoNotFoundException] Todo not found: {}", ex.getMessage());
        }
        return new ApiError(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleAllExceptions(Exception ex, HttpServletRequest request) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("[handleAllExceptions] Unexpected error: {}", ex.getMessage(), ex);
        }
        return new ApiError(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
    }
}
