package org.team.bookshop.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.logging.LogLevel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value", LogLevel.ERROR),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value", LogLevel.ERROR),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found", LogLevel.ERROR),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error", LogLevel.ERROR),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value", LogLevel.ERROR),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied", LogLevel.ERROR),
    NO_HANDLER_FOUND(404, "C007", "No Handler found", LogLevel.ERROR),
    NO_RESOURCE_FOUND(404, "C008", "No Resource found", LogLevel.ERROR),

    //USER
    EMAIL_ALREADY_REGISTERED(400, "U001", "Email already registered", LogLevel.ERROR),

    //BOOK

    //CATEGORY
    SELF_LOOP_CATEGORY_PATH(400, "C007", "The category path should not have self-loops", LogLevel.ERROR),

    //ORDER


    //PAYMENT

    //Scure
    SECURITY_CONFIGURATION_ERROR(500, "S001", "Security Configuration Error", LogLevel.ERROR),
    AUTHENTICATION_FAILURE(401, "S002", "Authentication Failure", LogLevel.ERROR);
    ;

    private final String code;
    private final String message;
    private int status;
    private final LogLevel logLevel;

    ErrorCode(final int status, final String code, final String message, LogLevel logLevel) {
    this.status = status;
    this.message = message;
    this.code = code;
    this.logLevel = logLevel;
    }

    public String getMessage() {
    return this.message;
    }

    public String getCode() {
    return code;
    }

    public int getStatus() {
    return status;
    }

    public LogLevel getLogLevel() {
    return logLevel;
    }

}