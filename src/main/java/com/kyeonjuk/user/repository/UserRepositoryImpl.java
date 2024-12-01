package com.kyeonjuk.user.repository;

import com.kyeonjuk.user.application.interfaces.UserRepository;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.repository.entity.UserEntity;
import com.kyeonjuk.user.repository.jpa.user.JpaUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor  // 필요한 생성자만 자동 생성
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    @Transactional
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

    @Override
    public List<User> findByNameContaining(String name) {
        List<User> userList = jpaUserRepository.findByNameContaining(name).stream()
            .map(UserEntity::toUser).toList();

        return userList;
    }
}
