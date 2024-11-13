package com.kyeonjuk.post.application.dto;

import com.kyeonjuk.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
