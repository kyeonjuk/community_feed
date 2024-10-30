package com.kyeonjuk.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kyeonjuk.auth.domain.RandomTokenGenerator;
import org.junit.jupiter.api.Test;

public class RandomTokenGeneratorTest {

    /*
        토큰이 notNull + 16자 인지 확인
     */
    @Test
    void whenGenerateToken_thenReturnTokenWithCorrectLength() {
        // when
        String token = RandomTokenGenerator.generateToken();

        // then
        assertNotNull(token);
        assertEquals(16, token.length());
    }

    /*
        토큰을 구성하는 패턴이 유효한지
     */
    @Test
    void whenGenerateToken_thenReturnTokenWithValidCharacters() {
        // when
        String token = RandomTokenGenerator.generateToken();

        // then
        assertNotNull(token);
        assertTrue(token.matches("[0-9A-Za-z]{16}"));
    }

    /*
        토큰 중복 방지
     */
    @Test
    void whenGenerateTokenMultipleTimes_thenReturnUniqueTokens() {
        // when
        String token1 = RandomTokenGenerator.generateToken();
        String token2 = RandomTokenGenerator.generateToken();

        // then
        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2);
    }

}
