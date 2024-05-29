package org.team.bookshop.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.team.bookshop.global.error.exception.ApiException;

import java.net.BindException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 각 도메인별 비지니스 CustomException 동작하는 메서드 입니다!.
     *
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e) {
        final ErrorCode errorCode = e.getErrorCode();

        switch (errorCode.getLogLevel()){
            case ERROR -> log.error(errorCode.getMessage());
            case WARN -> log.warn(errorCode.getMessage());
            default -> log.info(errorCode.getMessage());
        }
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    /**
     *  개발자가 캐치하지 못해서 나오는 원인 불명의 에러
     *  또는
     *  의도된 checkedException 처리가 되어야합니다.
     *
     * 관련 자료가 궁금하시다면
     * 이펙티브 자바, 쉽게 정리하기 - item 71. 필요 없는 검사 예외(checked error) 사용은 피하라 를 확인해주세요.
     *
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("ServerInternalException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
