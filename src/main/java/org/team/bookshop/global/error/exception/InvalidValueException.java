package org.team.bookshop.global.error.exception;

import org.team.bookshop.global.error.ErrorCode;

public class InvalidValueException extends ApiException{

    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
