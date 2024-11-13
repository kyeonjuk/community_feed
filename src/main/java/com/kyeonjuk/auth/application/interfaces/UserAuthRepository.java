package com.kyeonjuk.auth.application.interfaces;

import com.kyeonjuk.auth.domain.UserAuth;
import com.kyeonjuk.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth auth, User user);
    UserAuth loginUser(String email, String password, String fcmToken);
}
