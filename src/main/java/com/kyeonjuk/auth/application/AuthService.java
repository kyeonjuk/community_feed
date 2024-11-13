package com.kyeonjuk.auth.application;

import com.kyeonjuk.auth.application.dto.CreateUserAuthRequestDto;
import com.kyeonjuk.auth.application.dto.LoginRequestDto;
import com.kyeonjuk.auth.application.dto.UserAccessTokenResponseDto;
import com.kyeonjuk.auth.application.interfaces.EmailVerificationRepository;
import com.kyeonjuk.auth.application.interfaces.UserAuthRepository;
import com.kyeonjuk.auth.domain.Email;
import com.kyeonjuk.auth.domain.TokenProvider;
import com.kyeonjuk.auth.domain.UserAuth;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final TokenProvider tokenProvider;

    public Long registerUser(CreateUserAuthRequestDto dto) {

        // 가입 전 이메일 인증 여부 확인 -> 인증 된 이메일만 가입 가능
        Email email = Email.createEmail(dto.email());

        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }

        // User 생성
        UserInfo userInfo = new UserInfo(dto.name(), dto.ProfileImageUrl());
        User user = new User(null, userInfo);

        // UserAuth 생성
        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());

        // DB 저장
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }

    public UserAccessTokenResponseDto login(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password(), dto.fcmToken());

        // 정상 로그인 시 -> 토큰 생성
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getUserRole());

        return new UserAccessTokenResponseDto(token);
    }

}
