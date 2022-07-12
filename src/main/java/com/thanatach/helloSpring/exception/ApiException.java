package com.thanatach.helloSpring.exception;

import org.springframework.http.ResponseEntity;

public class ApiException extends BaseException {

    public ApiException(String message) {
        super("user."+message);
    }

    public static ApiException nullValue() {
        return new ApiException("request.value.null");
    }
    public static ApiException invalidValue() {
        return new ApiException("request.value.invalid");
    }
    public static ApiException nullResult() {
        return new ApiException("result.value.null");
    }
}
