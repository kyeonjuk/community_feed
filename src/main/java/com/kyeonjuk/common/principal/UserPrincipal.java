package com.kyeonjuk.common.principal;

import com.kyeonjuk.auth.domain.UserRole;
import lombok.Getter;

@Getter
public class UserPrincipal {

    private Long userId;
    private UserRole userRole;

    public UserPrincipal(Long userId, String role) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(role);
    }
}
