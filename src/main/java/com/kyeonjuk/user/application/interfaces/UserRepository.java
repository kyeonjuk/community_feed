package com.kyeonjuk.user.application.interfaces;

import com.kyeonjuk.user.domain.User;
import java.util.List;

public interface UserRepository {

    User save(User user);

    User findById(Long id);

    List<User> findByNameContaining(String name);
}
