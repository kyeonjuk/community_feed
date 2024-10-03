package com.kyeonjuk.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostTest {

    private Post post1;
    private PostContent postContent;
    private final UserInfo userInfo = new UserInfo("홍길동", "");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);

    @BeforeEach
    void init() {
        this.postContent = new PostContent("글작성합니다!");
        this.post1 = new Post(1L, user1, postContent);
    }

    /*
        좋아요
     */
    @Test
    void givenCreatePost_whenLike_thenCountIsOne() {
        // when
        post1.like(user2);

        // then
        assertEquals(1, post1.getLikeCount());
    }

    /*
        자기자신 좋아요 에러
     */
    @Test
    void givenCreatePost_whenLikeMyself_thenThrowError() {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> post1.like(user1));
    }

    /*
        싫어요
     */
    @Test
    void givenCreatePostAndLike_whenUnLike_thenCountIsZero() {
        // given
        post1.like(user2);

        // when
        post1.unLike();

        // then
        assertEquals(0, post1.getLikeCount());
    }

    /*
        수정
     */
    @Test
    void givenCreatePost_whenUpdatePost_thenSaved() {
        // when
        post1.updatePost(user1, "수정했습니다", PostPublicationState.PUBLIC);

        // then
        assertEquals("수정했습니다", post1.getContent());
        assertEquals(PostPublicationState.PUBLIC, post1.getState());
    }

    /*
        다른 사람이 수정 (권한 X) -> 예외
     */
    @Test
    void givenCreatePost_whenUpdatePostOther_thenThrowError() {

        // then
        assertThrows(IllegalArgumentException.class,
            () -> post1.updatePost(user2, "수정했습니다", PostPublicationState.PUBLIC));
    }
}