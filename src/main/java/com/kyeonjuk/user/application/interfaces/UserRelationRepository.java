package com.kyeonjuk.user.application.interfaces;

import com.kyeonjuk.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);
}
