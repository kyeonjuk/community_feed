package com.kyeonjuk.common.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 팔로우
    ALREADY_FOLLOWING_USER(409, "이미 팔로우한 사용자입니다."),

    // 포스트
    EMPTY_POST_CONTENT(400, "게시글 내용은 비어 있을 수 없습니다."),
    EXCEEDED_MAX_POST_LENGTH(400, "게시글 내용은 최대 500자까지 작성할 수 있습니다."),
    BELOW_MIN_POST_LENGTH(400, "게시글 내용은 최소 5자 이상이어야 합니다."),
    NULL_POST_ID(400, "해당 포스트가 존재하지 않습니다."),

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
