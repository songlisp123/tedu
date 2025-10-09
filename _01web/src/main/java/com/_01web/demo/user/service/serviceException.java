package com._01web.demo.user.service;

import com._01web.demo.base.response.StatusCode;
import lombok.Getter;


public class serviceException extends RuntimeException {
    @Getter
    private StatusCode statusCode;

    public serviceException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
