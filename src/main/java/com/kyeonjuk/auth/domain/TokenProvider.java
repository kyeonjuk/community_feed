package com.kyeonjuk.auth.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    // 시크릿키 설정을 위한 의존성 주입 (JWT 라이브러리 내장 기능)
    private final SecretKey key;

    // 토큰 만료 시간 설정
    private static final long TOKEN_VALID_TIME = 1000L * 60 * 60;   // 1 hour

    // [생성자] JWT 생성, 검증에 사용되는 비밀키 설정
    // ㄴ @Value("${변수명}) : appplication.yml의 해당 변수의 값을 가져옴 (springframework로 import)
    public TokenProvider(@Value("${secret-key}") String secretKey) {
        // SHA-256 알고리즘에 적합한 비밀키를 생성
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(Long userId, String role) {
        // Claims : JWT에서 페이로드에 담기는 정보(사용자,인증 정보) 객체
        Claims claims = Jwts.claims()
            .subject(userId.toString())     // subject에 userId 저장
            .build();

        // JWT 토큰 만료시간
        Date now = new Date();
        Date validateDate = new Date(now.getTime() + TOKEN_VALID_TIME);
        return Jwts.builder()
            .claims(claims)
            .issuedAt(now)  // 토큰 생성시간
            .expiration(validateDate)   // 토큰 유효기간
            .claim("role", role)    // 단일 클레임 추가
            .signWith(key)  // 시그니처 부분 (비밀키)
            .compact();     // JWT 문자열 생성
    }

    public Long getUserId(String token) {
        return Long.parseLong(              // 문자열로 반환된 사용자ID를 -> Long타입으로 변환
            Jwts.parser()                   // JWT parser 초기화
                .verifyWith(key)            // 비밀키로 토큰의 서명(시그니처) 검증
                .build()
                .parseSignedClaims(token)   // 검증된 JWT토큰의 Claim(payload)객체 파싱
                .getPayload()
                .getSubject()               // payload에서 사용자ID(subject)를 문자열 반환
        );
    }

    public String getUserRole(String token) {
        return Jwts.parser()                            // JWT parser 초기화
                .verifyWith(key)                        // 비밀키로 토큰의 서명(시그니처) 검증
                .build()
                .parseSignedClaims(token)               // 검증된 JWT토큰의 Claim(payload)객체 파싱
                .getPayload()
                .get("role", String.class);   // payload에서 role 단일클레임을 문자열 반환
    }

}
