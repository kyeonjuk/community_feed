package com.kyeonjuk.common.idempotency.repository;

import com.kyeonjuk.common.idempotency.repository.entity.IdempotencyEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity, Long> {

    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
