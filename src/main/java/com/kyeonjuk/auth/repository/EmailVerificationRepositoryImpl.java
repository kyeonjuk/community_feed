package com.kyeonjuk.auth.repository;

import com.kyeonjuk.auth.application.interfaces.EmailVerificationRepository;
import com.kyeonjuk.auth.domain.Email;
import com.kyeonjuk.auth.repository.entity.EmailVerificationEntity;
import com.kyeonjuk.auth.repository.jpa.JpaEmailVerificationRepository;
import com.kyeonjuk.common.domain.exception.ErrorCode;
import com.kyeonjuk.common.ui.BaseException;
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
                throw new BaseException(ErrorCode.EMAIL_ALREADY_VERIFIED);
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
            .orElseThrow(() -> new BaseException(ErrorCode.EMAIL_NOT_FOUND));

        if (entity.isVerified()) {
            throw new BaseException(ErrorCode.EMAIL_ALREADY_VERIFIED);
        }

        if (!entity.hasSameToken(token)) {
            throw new BaseException(ErrorCode.INVALID_TOKEN);
        }

        entity.verify();
    }

    /*
        이메일 인증 여부 확인
     */
    @Override
    public boolean isEmailVerified(Email email) {
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(email.getEmailText())
            .orElseThrow(() -> new BaseException(ErrorCode.EMAIL_NOT_FOUND));
        return entity.isVerified();
    }
}
