package com.dementei.ec.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private Integer status;
    private Integer errorCode;
    private String errorMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(Integer status, Integer errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
