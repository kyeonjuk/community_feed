package com.kyeonjuk.post.application.dto;

public record UpdateCommentRequestDto(Long userId, Long postId, String content) {
}
