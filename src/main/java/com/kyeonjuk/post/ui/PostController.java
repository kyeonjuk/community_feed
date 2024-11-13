package com.kyeonjuk.post.ui;

import com.kyeonjuk.common.idempotency.Idempotent;
import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.post.application.PostService;
import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.application.dto.LikePostRequestDto;
import com.kyeonjuk.post.application.dto.UpdatePostRequestDto;
import com.kyeonjuk.post.domain.Post;
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

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId,
        @RequestBody UpdatePostRequestDto dto) {

        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }

    @Idempotent
    @GetMapping("/like/{postId}/{userId}")
    public Response<Void> postLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId) {

        postService.likePost(new LikePostRequestDto(userId, postId));
        return Response.ok(null);
    }

    @GetMapping("/unlike/{postId}/{userId}")
    public Response<Void> postUnLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId) {

        postService.unlikePost(new LikePostRequestDto(userId, postId));
        return Response.ok(null);
    }

}
