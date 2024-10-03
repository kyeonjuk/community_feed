package com.kyeonjuk.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    private PostContent postContent;

    /*
        글자수 5미만 일 경우 -> 오류
     */
    @Test
    void givenPostContentSizeUnderFive_whenCheckText_thenThrowError() {
        // given
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> postContent = new PostContent("5미만"));
    }

    /*
        글자수 정상 (5이상 500미만)
     */
    @Test
    void givenPostContentNormal_whenCheckText_thenSuccess() {
        // given
        String content = "정상 포스트 입니다";

        // when
        PostContent postcontent = new PostContent(content);

        // when
        assertEquals(content, postcontent.contentText);
    }
    
    /*
        글자수 500이상 -> 오류 
     */
    @Test
    void givenPostContentSizeOver_whenCheckText_thenThrowError() {
        // given 
        String text = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> postContent = new PostContent(text));
    }

    /*
        글자 파라미터 값 적용
     */
    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵", "삵", "슭"})
    void givenPostContentSizeOverKorean_whenCheckText_thenThrowError(String koreaWord) {
        // given
        String text = koreaWord.repeat(501);
        System.out.println(text);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> postContent = new PostContent(text));
    }

    /*
        글자 null
     */
    @ParameterizedTest
    @NullAndEmptySource
    void givenPostContentNull_whenCheckText_thenThrowError(String value) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> postContent = new PostContent(value));
    }
}

