package org.team.bookshop.global.error.exception;

import org.team.bookshop.global.error.ErrorCode;

public class ApiException extends RuntimeException{

    private ErrorCode errorCode;

    public ApiException(Error message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
