package com.kyeonjuk.common.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 일반적인 오류 코드
    INVALID_INPUT_VALUE(400, "invalid input value"),
    NOT_FOUND(404, "not found data"),
    INTERNAL_ERROR(500, "unexpected error"),

    // 로그인, 이메일 인증 관련 오류
    ENTITY_NOT_FOUND(400, "존재하지 않는 사용자입니다."),
    EMAIL_ALREADY_VERIFIED(400, "이미 인증된 이메일입니다"),
    INVALID_TOKEN(400, "유효하지 않은 토큰입니다"),  // 토큰이 유효하지 않은 경우
    EMAIL_NOT_FOUND(404, "인증 요청하지 않은 이메일입니다");  // 이메일이 없을 경우

    private final Integer code;
    private final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
