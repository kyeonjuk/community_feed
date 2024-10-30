package com.kyeonjuk.auth;

import static org.junit.jupiter.api.Assertions.*;

import com.kyeonjuk.auth.domain.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    // 이메일이 없을 때
    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    // 유효하지않은 이메일 일 때
    @ParameterizedTest
    @ValueSource(strings = {"vaild@/ab", "a@안녕.com", "natty#@naver", "tset@/Test.com"})
    void givenInValidEmail_whenCreate_thenThrowError(String email) {
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    // 정상 이메일
    @ParameterizedTest
    @ValueSource(strings = {"vaild@ab", "a@naver.com", "natty@naver", "tset@test.com"})
    void givenEmailValid_whenCreate_thenReturnEmail(String email) {
        // given

        // when
        Email emailValue = Email.createEmail(email);

        // then
        assertEquals(email, emailValue.getEmailText());
    }

}