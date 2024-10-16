package com.kyeonjuk.post.application.dto;

import com.kyeonjuk.post.domain.Post;

public record GetPostResponseDto(Long postId, Long authorId, String authorName, String content,
                                 String state, Integer likeCount) {

    public GetPostResponseDto(Post post) {
        this(post.getId(), post.getAuthorId(), post.getAuthor().getName() ,post.getContent(),
            post.getState().toString(), post.getLikeCount());
    }
}
