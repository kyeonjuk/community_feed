package com.kyeonjuk.user.repository;

import com.kyeonjuk.user.application.interfaces.UserRepository;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.repository.entity.UserEntity;
import com.kyeonjuk.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor  // 필요한 생성자만 자동 생성
public class UserRepositoryImpl implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  @Override
  public User save(User user) {
    UserEntity userEntity = new UserEntity(user);
    userEntity = jpaUserRepository.save(userEntity);

    return userEntity.toUser();
  }

  @Override
  public User findById(Long id) {
    UserEntity userEntity = jpaUserRepository
        .findById(id)
        .orElseThrow(IllegalArgumentException::new);

    return userEntity.toUser();
  }
}
