package com.kyeonjuk.user.repository.jpa.user;

import com.kyeonjuk.user.repository.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    // userId가 리스트에 포함된 모든 사용자 조회
    List<UserEntity> findAllByIdIn(List<Long> ids);

    // 특정 문자열을 포함하는 사용자 검색 (Contains)
    List<UserEntity> findByNameContaining(String name);

}
