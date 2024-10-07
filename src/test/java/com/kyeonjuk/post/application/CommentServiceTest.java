package com.kyeonjuk.post.application;

import static org.junit.jupiter.api.Assertions.*;

import com.kyeonjuk.fake.FakeObjectFactory;
import com.kyeonjuk.post.application.dto.CreateCommentRequestDto;
import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.application.dto.LikeCommentRequestDto;
import com.kyeonjuk.post.application.dto.UpdateCommentRequestDto;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.domain.User;
import org.junit.jupiter.api.Test;

class CommentServiceTest {

    private final CommentService commentService = FakeObjectFactory.getCommentService();
    private final UserService userService = FakeObjectFactory.getUserService();
    private final PostService postService = FakeObjectFactory.getPostService();

    private final CreateUserRequestDto userDto = new CreateUserRequestDto("홍길동", "");
    private final User user = userService.createUser(userDto);
    private final User otherUser = userService.createUser(userDto);

    private final CreatePostRequestDto postDto =
        new CreatePostRequestDto(user.getId(), "글 작성합니다.", PostPublicationState.PUBLIC);
    private final Post post = postService.createPost(postDto);

    /*
        CreateComment
     */
    @Test
    void givenCreateComment_whenSave_thenSuccess() {
        // given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(), user.getId(), "댓글입니다.");

        // when
        Comment saveComment = commentService.createComment(dto);

        // then
        assertEquals(dto.content(), saveComment.getContent());
    }

    /*
        UpdateComment
     */
    @Test
    void givenCreateComment_whenUpdate_thenSuccess() {
        // given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(), user.getId(), "댓글입니다.");
        Comment comment = commentService.createComment(dto);

        // when
        UpdateCommentRequestDto updateDto =
            new UpdateCommentRequestDto(comment.getId(), comment.getAuthorId(), comment.getPostId(), "댓글 수정합니다.");
        Comment updateComment = commentService.updateComment(updateDto);

        // then
        assertEquals(updateDto.content(), updateComment.getContent());
    }

    /*
        LikeComment
     */
    @Test
    void givenCreateCommentAndUser_whenLike_thenLikeCountIsOne() {
        // given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(), user.getId(), "댓글입니다.");
        Comment comment = commentService.createComment(dto);

        // when
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(otherUser.getId(), comment.getId());
        commentService.likeComment(likeDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    /*
        LikeComment (same User)
     */
    @Test
    void givenCreateComment_whenLikeSameUser_thenThrowError() {
        // given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(), user.getId(), "댓글입니다.");
        Comment comment = commentService.createComment(dto);

        // when
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(user.getId(), comment.getId());

        // then
        assertThrows(IllegalArgumentException.class, () -> commentService.likeComment(likeDto));
    }


    /*
        UnlikeComment
     */
    @Test
    void givenCreateCommentAndUserAndLike_whenUnlike_thenLikeCountIsZero() {
        // given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(), user.getId(), "댓글입니다.");
        Comment comment = commentService.createComment(dto);
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(otherUser.getId(), comment.getId());
        commentService.likeComment(likeDto);

        // then
        commentService.unlikeComment(likeDto);

        // then
        assertEquals(0, comment.getLikeCount());
    }
}