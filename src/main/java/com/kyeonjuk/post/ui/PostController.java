package com.kyeonjuk.post.ui;

import com.kyeonjuk.common.idempotency.Idempotent;
import com.kyeonjuk.common.principal.AuthPrincipal;
import com.kyeonjuk.common.principal.UserPrincipal;
import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.post.application.PostService;
import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.application.dto.LikePostRequestDto;
import com.kyeonjuk.post.application.dto.UpdatePostRequestDto;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.repository.post_queue.UserPostQueueQueryRepository;
import com.kyeonjuk.post.ui.dto.GetContentResponseDto;
import com.kyeonjuk.post.ui.dto.GetPostMainResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @PostMapping
    public Response<Long> createPost(@ModelAttribute CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId,
        @RequestBody UpdatePostRequestDto dto){
        Post post = postService.updatePost(postId,dto);
        return Response.ok(post.getId());
    }
    @Idempotent
    @GetMapping("/like/{postId}/{userId}")
    public Response<Void> postLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId){

        postService.likePost(new LikePostRequestDto(userId,postId));
        return Response.ok(null);
    }

    @GetMapping("/unlike/{postId}/{userId}")
    public Response<Void> postUnLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId){

        postService.unlikePost(new LikePostRequestDto(userId,postId));
        return Response.ok(null);
    }

    @GetMapping("/getPost/{postId}")
    public Response<GetPostMainResponseDto> post(@AuthPrincipal UserPrincipal userPrincipal,
                                                 @PathVariable(name = "postId") Long postId) {

        // 내 userId 가져오기
        Long userId = userPrincipal.getUserId();

        Post post = postService.getPost(postId);
        List<GetContentResponseDto> comment = userPostQueueQueryRepository.getCommentResponse(postId, post.getAuthorId(),0L);

        GetPostMainResponseDto result = new GetPostMainResponseDto(post,comment, userId, post.getAuthorId());

        return Response.ok(result);
    }
}

