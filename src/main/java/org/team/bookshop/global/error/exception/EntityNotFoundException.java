package org.team.bookshop.global.error.exception;

import org.team.bookshop.global.error.ErrorCode;

public class EntityNotFoundException extends ApiException{

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}
