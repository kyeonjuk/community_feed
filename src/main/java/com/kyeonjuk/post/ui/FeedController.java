package com.kyeonjuk.post.ui;

import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.post.repository.post_queue.UserPostQueueQueryRepository;
import com.kyeonjuk.post.ui.dto.GetContentResponseDto;
import com.kyeonjuk.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(name = "userId") Long userId, Long lastPostId) {
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(userId, lastPostId);

        return Response.ok(result);
    }

    @GetMapping("/comment/{userId}/{postId}")
    public Response<List<GetContentResponseDto>> getCommentFeed(@PathVariable(name = "postId") Long postId, Long lastCommentId,
                                                                @PathVariable(name = "userId") Long userId) {
        List<GetContentResponseDto> result = userPostQueueQueryRepository.getCommentResponse(postId, lastCommentId, userId);

        return Response.ok(result);
    }
}
