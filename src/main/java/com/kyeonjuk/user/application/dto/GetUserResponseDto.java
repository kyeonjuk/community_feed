package com.kyeonjuk.user.application.dto;

import com.kyeonjuk.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImageUrl,
                                 Integer followingCount, Integer followerCount) {

    // record 생성자 작성법
    // ㄴ this(); = 괄호 안에 필드의 값들이 순서대로 들어감
    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImageUrl(), user.getFollowingCount(),
            user.getFollowerCount());
    }
}
