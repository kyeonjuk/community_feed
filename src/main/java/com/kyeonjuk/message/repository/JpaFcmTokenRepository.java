package com.kyeonjuk.message.repository;

import com.kyeonjuk.message.repository.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {

    void deleteAllByFcmToken(String token);
}
