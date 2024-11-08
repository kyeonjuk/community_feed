package com.kyeonjuk.acceptance.utils;

import static com.kyeonjuk.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static com.kyeonjuk.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static com.kyeonjuk.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static com.kyeonjuk.acceptance.steps.UserAcceptanceSteps.followUser;

import com.kyeonjuk.auth.application.dto.CreateUserAuthRequestDto;
import com.kyeonjuk.auth.application.dto.SendEmailRequestDto;
import com.kyeonjuk.user.application.dto.FollowUserRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

// 샘플데이터 생성 + 데이터 조회
@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        // user 1, 2, 3 생성
        for (int i = 1; i < 4; i++) {
            createUser("user" + i + "@test.com");
        }

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?", String.class)
            .setParameter(1, email)
            .getSingleResult()
            .toString();
    }

    /*
        해당 이메일 인증 여부 확인
     */
    public boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT isVerified FROM EmailVerificationEntity WHERE email = :email", Boolean.class)
            .setParameter("email", email)
            .getSingleResult();
    }

    /*
        유저 아이디 조회
     */
    public Long getUserId(String email) {
        return (Long) entityManager.createQuery("SELECT userId FROM UserAuthEntity WHERE email = :email", Long.class)
            .setParameter("email", email)
            .getSingleResult();
    }


    /*
        회원가입
     */
    public void createUser(String email) {
        requestSendEmail(new SendEmailRequestDto(email));   // 이메일 전송
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);   // 이메일 인증
        // 회원가입
        registerUser(new CreateUserAuthRequestDto(email, "password", "USER", "name", ""));
    }
}
