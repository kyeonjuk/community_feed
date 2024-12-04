package com.kyeonjuk.common.ui;

import com.kyeonjuk.common.domain.exception.ErrorCode;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // BaseException 처리
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Response<Void>> handleBaseException(BaseException exception) {
        log.error("BaseException: {}", exception.getMessage(), exception);
        HttpStatus status = HttpStatus.valueOf(exception.getCode());
        return ResponseEntity.status(status).body(Response.error(exception));
    }

    // 아이디가 없을 때 발생할 수 있는 예외 처리 (NoSuchElementException 등)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response<Void>> handleNoSuchElementException(NoSuchElementException exception) {
        log.error("NoSuchElementException: {}", exception.getMessage(), exception);
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Response.error(new BaseException(ErrorCode.ENTITY_NOT_FOUND, "존재하지 않는 사용자입니다.")));
    }

    // 잘못된 데이터 액세스 (예: 데이터베이스 관련)
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Response<Void>> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception) {
        log.error("InvalidDataAccessApiUsageException: {}", exception.getMessage(), exception);
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Response.error(new BaseException(ErrorCode.INTERNAL_ERROR, "서버 내부 오류가 발생했습니다.")));
    }

    // 모든 예외를 처리 (예상치 못한 예외)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleGenericException(Exception exception) {
        log.error("Unhandled Exception: {}", exception.getMessage(), exception);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Response.error(new BaseException(ErrorCode.INTERNAL_ERROR, "서버 내부 오류가 발생했습니다.")));
    }
}


