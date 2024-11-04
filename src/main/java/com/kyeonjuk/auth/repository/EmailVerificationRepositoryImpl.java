package com.kyeonjuk.auth.repository;

import com.kyeonjuk.auth.application.interfaces.EmailVerificationRepository;
import com.kyeonjuk.auth.domain.Email;
import com.kyeonjuk.auth.repository.entity.EmailVerificationEntity;
import com.kyeonjuk.auth.repository.jpa.JpaEmailVerificationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();
        Optional<EmailVerificationEntity> emailVerificationEntity =
            jpaEmailVerificationRepository.findByEmail(emailAddress);

        if (emailVerificationEntity.isPresent()) {  // 값이 있을 때 인증된건지 확인

            // optional 벗기기
            EmailVerificationEntity emailVerificationEntity1 = emailVerificationEntity.get();

            if (emailVerificationEntity1.isVerified()) {    // 인증이 이미 되어있다면
                throw new IllegalArgumentException("이미 인증된 이메일 입니다.");
            }

            // 인증처리
            emailVerificationEntity1.verify();
            return;
        }

        EmailVerificationEntity emailVerificationEntity1 = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity1);
    }

    /*
        이메일 인증
     */
    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailAddress = email.getEmailText();

        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(emailAddress)
            .orElseThrow(() -> new IllegalArgumentException("인증 요청하지 않은 이메일입니다."));

        if (entity.isVerified()) {
            throw new IllegalArgumentException("이미 인증된 이메일입니다");
        }

        if (!entity.hasSameToken(token)) {
            throw new IllegalArgumentException("토큰 값이 유효하지 않습니다.");
        }

        entity.verify();
    }
}
