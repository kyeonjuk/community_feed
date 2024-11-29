package com.kyeonjuk.auth.repository;

import com.kyeonjuk.auth.application.interfaces.UserAuthRepository;
import com.kyeonjuk.auth.domain.UserAuth;
import com.kyeonjuk.auth.repository.entity.UserAuthEntity;
import com.kyeonjuk.auth.repository.jpa.JpaUserAuthRepository;
import com.kyeonjuk.message.repository.JpaFcmTokenRepository;
import com.kyeonjuk.message.repository.entity.FcmTokenEntity;
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
    private final JpaFcmTokenRepository jpaFcmTokenRepository;

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
    public UserAuth loginUser(String email, String password, String fcmToken) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("옳지 않은 비밀번호 입니다.");
        }

        userAuthEntity.updateLastLoginAt();

        // 기존의 같은 FCM 토큰을 가지고 있는 정보 삭제
        jpaFcmTokenRepository.deleteAllByFcmToken(fcmToken);

        // FCM 알림 토큰 저장
        jpaFcmTokenRepository.save(new FcmTokenEntity(userAuth.getUserId(), fcmToken));

        return userAuth;
    }
}
