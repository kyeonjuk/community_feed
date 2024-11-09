package com.kyeonjuk.acceptance;

import static com.kyeonjuk.acceptance.steps.FeedAcceptanceSteps.reqCreatePost;
import static com.kyeonjuk.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static com.kyeonjuk.acceptance.steps.FeedAcceptanceSteps.requestFeedCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kyeonjuk.acceptance.utils.AcceptanceTestTemplate;
import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    private String token;

    /*
     *  User 1 --> follow --> User2
     *  User 1 --> follow --> User3
     * */
    @BeforeEach
    void setUp(){
        super.init();
        this.token = login("user1@test.com");
    }

    /*
     *  User 2 create post 1
     *  User 1 get post 1 from feed
     */
    @Test
    void givenUser2CreatePost1_whenUser1GetPost1_thenCheck() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "유저2가 작성합니다", PostPublicationState.PUBLIC);
        Long createPostId = reqCreatePost(dto);

        // when
        List<GetPostContentResponseDto> result = requestFeed(token);

        // then
        assertEquals(1, result.size());
        assertEquals(createPostId, result.get(0).getId());
    }

    @Test
    void givenUserHasFollower_whenFollowerUserRequestFeedWithInvalidToken_thenFollowerCanGetPostFromFeed() {
        // given
        // when
        Integer code = requestFeedCode("abcd");

        // then
        assertEquals(400, code);
    }
}
