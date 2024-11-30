package com.kyeonjuk.common.principal;

import com.kyeonjuk.auth.domain.UserRole;
import com.kyeonjuk.user.domain.User;
import lombok.Getter;

// 커스텀_어노테이션의 return 값
@Getter
public class UserPrincipal {

    private Long userId;
    private UserRole userRole;

    public UserPrincipal(Long userId, String role) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(role);
    }

    // User 객체로 변환하는 메서드 추가
    public User toUser() {
        return User.builder()
            .id(this.userId)
            .build();
    }
}
