package com.brash.digital_bookshelf.exception;

import lombok.Data;

@Data
public class ExceptionBodyResponse {

    private boolean error;
    private int code;
    private String message;

    public ExceptionBodyResponse(int code, String message) {
        this.error = true;
        this.code = code;
        this.message = message;
    }
}
