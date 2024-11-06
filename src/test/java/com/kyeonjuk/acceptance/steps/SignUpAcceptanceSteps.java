package com.kyeonjuk.acceptance.steps;

import com.kyeonjuk.auth.application.dto.CreateUserAuthRequestDto;
import com.kyeonjuk.auth.application.dto.SendEmailRequestDto;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;

// 회원가입 이메일 API 테스트
public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/signup/send-verification-email")
            .then()
            .extract()
            .jsonPath().get("code");    // response 의 code 를 가져옴
    }

    /*
        이메일과 토큰을 검증
     */
    public static Integer requestVerifyEmail(String email, String token) {
        return RestAssured
            .given()
            .queryParam("email", email)
            .queryParam("token", token)
            .when()
            .get("/signup/verify-token")
            .then()
            .extract()
            .jsonPath().get("code");    // response 의 code 를 가져옴
    }

    /*
        회원 가입
     */
    public static Integer registerUser(CreateUserAuthRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/signup/register")
            .then()
            .extract()
            .jsonPath().get("code");    // response 의 code 를 가져옴
    }
}

