package com.kyeonjuk.post.application.dto;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.CommentContent;
import com.kyeonjuk.user.domain.User;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {
}
