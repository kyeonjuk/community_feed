package com.kyeonjuk.acceptance.auth;

import static com.kyeonjuk.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static com.kyeonjuk.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kyeonjuk.acceptance.utils.AcceptanceTestTemplate;
import com.kyeonjuk.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
        this.createUser(email); // 유저 생성
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "password");

        // when
        String token = requestLoginGetToken(dto);

        // then
        assertNotNull(token);
//        String testEmailToken = getEmailToken(email);
//        assertEquals(testEmailToken, token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero() {
        // given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password");

        // when
        Integer code = requestLoginGetResponseCode(dto);

        // then
        assertEquals(400, code);
    }

}
