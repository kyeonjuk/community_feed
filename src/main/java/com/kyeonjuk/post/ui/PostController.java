package com.kyeonjuk.post.ui;

import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.post.application.CommentService;
import com.kyeonjuk.post.application.PostService;
import com.kyeonjuk.post.application.dto.CreateCommentRequestDto;
import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.application.dto.LikeCommentRequestDto;
import com.kyeonjuk.post.application.dto.LikePostRequestDto;
import com.kyeonjuk.post.application.dto.UpdateCommentRequestDto;
import com.kyeonjuk.post.application.dto.UpdatePostRequestDto;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/post1")
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PostMapping("/comment1")
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId,
        @RequestBody UpdatePostRequestDto dto) {

        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId,
        @RequestBody UpdateCommentRequestDto dto) {

        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

    @GetMapping("/like/{postId}/{userId}")
    public Response<Void> postLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId) {

        postService.likePost(new LikePostRequestDto(userId, postId));
        return Response.ok(null);
    }

    @GetMapping("/comment/like/{commentId}/{userId}")
    public Response<Void> commentLike(@PathVariable(name = "commentId") Long commentId,
        @PathVariable(name = "userId") Long userId) {

        commentService.likeComment(new LikeCommentRequestDto(userId, commentId));
        return Response.ok(null);
    }

    @GetMapping("/unlike/{postId}/{userId}")
    public Response<Void> postUnLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId) {

        postService.unlikePost(new LikePostRequestDto(userId, postId));
        return Response.ok(null);
    }

    @GetMapping("/comment/unlike/{commentId}/{userId}")
    public Response<Void> commentUnLike(@PathVariable(name = "commentId") Long commentId,
        @PathVariable(name = "userId") Long userId) {

        commentService.unlikeComment(new LikeCommentRequestDto(userId, commentId));
        return Response.ok(null);
    }

}
