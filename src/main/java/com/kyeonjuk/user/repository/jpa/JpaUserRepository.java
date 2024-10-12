package com.kyeonjuk.user.repository.jpa;

import com.kyeonjuk.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
