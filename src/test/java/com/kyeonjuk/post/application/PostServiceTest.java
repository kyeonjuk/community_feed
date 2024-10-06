package com.kyeonjuk.post.application;

import static org.junit.jupiter.api.Assertions.*;

import com.kyeonjuk.fake.FakeObjectFactory;
import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.application.dto.LikePostRequestDto;
import com.kyeonjuk.post.application.dto.UpdatePostRequestDto;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    private final PostService postService = FakeObjectFactory.getPostService();
    private final UserService userService = FakeObjectFactory.getUserService();

    private final CreateUserRequestDto userDto = new CreateUserRequestDto("고길동", "");
    private final User user = userService.createUser(userDto);
    private final User otherUser = userService.createUser(userDto);

    private final CreatePostRequestDto postDto = new CreatePostRequestDto(user.getId(), "글 작성 합니다.", PostPublicationState.PUBLIC);

    /*
        CreatePost
     */
    @Test
    void givenCreateUserAndPost_whenSave_thenSuccess() {
        // given
        Post savePost = postService.createPost(postDto);

        // when
        Post newPost = postService.getPost(savePost.getId());

        // then
        assertEquals(savePost, newPost);
    }

    /*
        getPost
     */
    @Test
    void givenCreatePost_whenGetPost_thenSame() {
        // given
        Post post = postService.createPost(postDto);

        // when
        Post getPost = postService.getPost(post.getId());

        // then
        assertEquals(post.getId(), getPost.getId());
        assertEquals("글 작성 합니다.", getPost.getContent());
    }

    /*
        updatePost
     */
    @Test
    void givenCreatePost_whenUpdate_thenSuccess() {
        // given
        Post post = postService.createPost(postDto);
        UpdatePostRequestDto updateDto =
            new UpdatePostRequestDto(post.getId(), post.getAuthorId(), "수정합니다.", post.getState());

        // when
        postService.updatePost(updateDto);

        // then
        assertEquals(updateDto.content(), post.getContent());
    }

    /*
        LikePost (otherUser)
     */
    @Test
    void givenCreatePost_whenLikeOtherUser_thenSuccess() {
        // given
        Post post = postService.createPost(postDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(otherUser.getId(), post.getId());

        // when
        postService.likePost(likePostRequestDto);

        // then
        assertEquals(1, post.getLikeCount());
    }

    /*
        LikePost (sameUser)
     */
    @Test
    void givenCreatePost_whenLikeSameUser_thenThrowError() {
        // given
        Post post = postService.createPost(postDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(user.getId(), post.getId());

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> postService.likePost(likePostRequestDto));
    }

    /*
        UnlikePost (Like after)
     */
    @Test
    void givenCreatePostAndLike_whenUnLike_thenSuccess() {
        // given
        Post post = postService.createPost(postDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(otherUser.getId(), post.getId());
        postService.likePost(likePostRequestDto);

        // when
        postService.unlikePost(likePostRequestDto);

        // then
        assertEquals(0, post.getLikeCount());
    }
}