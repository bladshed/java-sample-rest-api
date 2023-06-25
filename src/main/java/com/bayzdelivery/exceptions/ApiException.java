package com.bayzdelivery.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException {

    private final int status;
    private final HttpStatus error;
    private final String message;

    public ApiException(int status, HttpStatus error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "status=" + status +
                ", error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
