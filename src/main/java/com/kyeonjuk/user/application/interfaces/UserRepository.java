package com.kyeonjuk.user.application.interfaces;

import com.kyeonjuk.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
}
