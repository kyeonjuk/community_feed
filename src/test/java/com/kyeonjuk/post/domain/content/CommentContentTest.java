package com.kyeonjuk.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    private CommentContent commentContent;

    /*
        comment null
     */
    @ParameterizedTest
    @NullAndEmptySource
    void givenCommentIsNull_whenCheck_thenThrowError(String value) {
        // given
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }

    /*
        comment 100 over
     */
    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭"})
    void givenCommentIsOverAndKorean_whenCheck_thenThrowError(String value) {
        // given
        String text = value.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(text));
    }

    /*
        정상 Comment
     */
    @Test
    void givenCommentIsNormal_whenCheck_thenSuccess() {
        // given
        String text = "정상";

        // when
        commentContent = new CommentContent(text);

        // then
        assertEquals(text, commentContent.getContentText());
    }
}