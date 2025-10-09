package com.ivos.demo.base.exception;

import com.ivos.demo.base.response.StatusCode;
import lombok.Getter;


public class serviceException extends RuntimeException {

    @Getter
    private StatusCode statusCode;

    public serviceException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
