package com.kyeonjuk.common.ui;

import com.kyeonjuk.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {

    public static <T> Response<T> ok(T value) {
        return new Response<>(0, "ok", value);
    }

    public static <T> Response<T> error(ErrorCode error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}
