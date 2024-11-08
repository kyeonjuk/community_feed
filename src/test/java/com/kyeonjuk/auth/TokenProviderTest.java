package com.kyeonjuk.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.kyeonjuk.auth.domain.TokenProvider;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    private final String secretKey = "asdifuwiejrp2iu3wieaskidfhakjehrkuhckbnxckjwhsweirhai";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() {
        // given
        Long userId = 1L;
        String role = "ADMIN";

        // when
        String token = tokenProvider.createToken(userId, role);

        // then
        Long testUserId = tokenProvider.getUserId(token);
        String testUserRole = tokenProvider.getUserRole(token);

        assertEquals(userId, testUserId);
        assertEquals(role, testUserRole);
    }

    @Test
    void givenInvalidToken_whenGetUserId_thenThrowError() {
        // given
        String token = "";

        // then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(token));
    }

}
