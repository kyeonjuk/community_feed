package com.kyeonjuk.post.application.dto;

import com.kyeonjuk.post.domain.content.PostPublicationState;
import org.springframework.web.multipart.MultipartFile;

public record CreatePostRequestDto(Long userId, String content, MultipartFile contentImageUrl, PostPublicationState state) {
}
