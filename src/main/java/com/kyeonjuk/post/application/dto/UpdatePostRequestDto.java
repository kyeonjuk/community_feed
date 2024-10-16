package com.kyeonjuk.post.application.dto;

import com.kyeonjuk.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {

}
