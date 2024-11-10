package com.kyeonjuk.auth.repository;

import com.kyeonjuk.auth.application.interfaces.UserAuthRepository;
import com.kyeonjuk.auth.domain.UserAuth;
import com.kyeonjuk.auth.repository.entity.UserAuthEntity;
import com.kyeonjuk.auth.repository.jpa.JpaUserAuthRepository;
import com.kyeonjuk.user.application.interfaces.UserRepository;
import com.kyeonjuk.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    public UserAuth registerUser(UserAuth auth, User user) {
        // 회원 정보 (User) 저장
        User savedUser = userRepository.save(user);

        // UserAuth 저장
        UserAuthEntity userAuthEntity = new UserAuthEntity(auth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);

        return userAuthEntity.toUserAuth();
    }

    /*
        로그인
     */
    @Override
    @Transactional
    public UserAuth loginUser(String email, String password) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("옳지 않은 비밀번호 입니다.");
        }

        userAuthEntity.updateLastLoginAt();

        return userAuth;
    }
}
