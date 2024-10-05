package com.kyeonjuk.post.application.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, Long postId, String content) {
}
