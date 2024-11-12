package com.kyeonjuk.acceptance.auth;

import static com.kyeonjuk.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static com.kyeonjuk.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static com.kyeonjuk.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kyeonjuk.acceptance.utils.AcceptanceTestTemplate;
import com.kyeonjuk.auth.application.dto.CreateUserAuthRequestDto;
import com.kyeonjuk.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@gmail.com";

    @BeforeEach
    void setUp() {
        this.cleanUp(); // 데이터 초기화만 진행
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved() {
        // given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        // when
        Integer code = requestSendEmail(dto);

        // then
        String token = this.getEmailToken(email);   // DB 에서 토큰 가져오기
        assertNotNull(token);
        assertEquals(0, code);  // HTTP 0번 에러
    }

    /*
        유효하지 않은 이메일
     */
    @Test
    void givenInvalidEmail_whenSendEmail_thenVerificationTokenNotSaved() {
        // given
        SendEmailRequestDto dto = new SendEmailRequestDto("abcd");

        // when
        Integer code = requestSendEmail(dto);

        // then
        assertEquals(400, code);  // HTTP 400번 client 에러
    }

    /*
        정상 이메일, 토큰 확인
     */
    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail(email, token);

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(0, code);
        assertTrue(isEmailVerified);

    }

    /*
        정상 이메일 + 잘못된 토큰 일 때
     */
    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        Integer code = requestVerifyEmail(email, "wrong token");

        // then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(400, code);
        assertFalse(isEmailVerified);
    }

    /*
        인증이 완료된 이메일에 재인증시도 할 때
     */
    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // when
        Integer code = requestVerifyEmail(email, token);

        // then
        assertEquals(400, code);
    }

    /*
        인증할 이메일이 잘못된 경우
     */
    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError() {
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail("wrong email", token);

        // then
        assertEquals(400, code);
    }

    /*
        정상 회원 가입
     */
    @Test
    void  givenVerifiedEmail_whenRegister_thenUserRegistered(){
        // given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "1111", "USER", "홍길동", "");
        Integer code = registerUser(dto);

        // then
        assertEquals(0, code);
        Long userId = getUserId(email);
        assertEquals(1L, userId);
    }

    /*
        인증하지 않은 이메일로 가입
     */
    @Test
    void givenUnverifiedEmail_whenRegister_thenThrowError(){
        // given
        requestSendEmail(new SendEmailRequestDto(email));

        // when
        CreateUserAuthRequestDto dto = new CreateUserAuthRequestDto(email, "1111", "USER", "홍길동", "");
        Integer code = registerUser(dto);

        // then
        assertEquals(400, code);
    }

}
