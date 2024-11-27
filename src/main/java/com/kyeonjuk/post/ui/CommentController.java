package com.kyeonjuk.post.ui;

import com.kyeonjuk.common.principal.AuthPrincipal;
import com.kyeonjuk.common.principal.UserPrincipal;
import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.post.application.CommentService;
import com.kyeonjuk.post.application.dto.CreateCommentRequestDto;
import com.kyeonjuk.post.application.dto.LikeCommentRequestDto;
import com.kyeonjuk.post.application.dto.UpdateCommentRequestDto;
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
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto){
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId,
        @RequestBody UpdateCommentRequestDto dto){
        Comment comment = commentService.updateComment(commentId,dto);
        return Response.ok(comment.getId());
    }

    @GetMapping("/like/{commentId}")
    public Response<Integer> commentLike(@PathVariable(name = "commentId") Long commentId,
        @AuthPrincipal UserPrincipal userPrincipal){

        int likeCount = commentService.likeComment(new LikeCommentRequestDto(userPrincipal.getUserId(),commentId));
        return Response.ok(likeCount);
    }

    @GetMapping("/unlike/{commentId}")
    public Response<Integer> commentUnLike(@PathVariable(name = "commentId") Long commentId,
        @AuthPrincipal UserPrincipal userPrincipal){

        int likeCount = commentService.unlikeComment(new LikeCommentRequestDto(userPrincipal.getUserId(),commentId));
        return Response.ok(likeCount);
    }

}