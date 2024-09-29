package com.kyeonjuk.user.application.interfaces;

import com.kyeonjuk.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
}
