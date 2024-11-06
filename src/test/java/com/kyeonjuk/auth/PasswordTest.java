package com.kyeonjuk.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kyeonjuk.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PasswordTest {


    /*
        패스워드 확인 일치 (정상)
     */
    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        // given
        Password password = Password.createEncryptPassword("password");

        // when, then
        assertTrue(password.matchPassword("password"));
    }

    /*
        패스워드 확인 불일치
     */
    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnFalse() {
        // given
        Password password = Password.createEncryptPassword("password");

        // when, then
        assertFalse(password.matchPassword("wrong password"));
    }

    /*
        패스워드가 null 일 경우
     */
    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowError(String password) {
        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptPassword(password));
    }
}
