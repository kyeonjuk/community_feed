package com.kyeonjuk.user.application.interfaces;

import com.kyeonjuk.user.domain.User;

public interface UserRepository {

    User save(User user);

    User findById(Long id);
}
