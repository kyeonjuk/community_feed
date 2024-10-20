package com.kyeonjuk.post.repository.post_queue;

import com.kyeonjuk.post.ui.dto.GetPostContentResponseDto;
import java.util.List;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);
}
