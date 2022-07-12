package com.thanatach.helloSpring.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Time;

@ControllerAdvice
public class AdvisorException {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException e) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        response.error = e.getMessage();
        response.stamp = System.currentTimeMillis();
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }

    @Data
    public static class ErrorResponse {
        public int status;
        public String error;
        public long stamp;
    }

}
