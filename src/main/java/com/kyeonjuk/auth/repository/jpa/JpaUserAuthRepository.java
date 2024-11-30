package com.kyeonjuk.auth.repository.jpa;

import com.kyeonjuk.auth.repository.entity.UserAuthEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {

    Optional<UserAuthEntity> findByUserId(Long userId);
}
