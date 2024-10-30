package com.kyeonjuk.auth.domain;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

// 유효성을 확인해주는 클래스
@RequiredArgsConstructor
public class Email {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;

    public static Email createEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is not valid");
        }

        // 이메일 형식 검사
        if (isNotValidEmailString(email)) {
            throw new IllegalArgumentException("email is not valid");
        }

        return new Email(email);
    }

    public String getEmailText() {
        return this.emailText;
    }

    private static boolean isNotValidEmailString(String email) {
        return !pattern.matcher(email).matches();
    }
}
