package com.kyeonjuk.auth.application;

import com.kyeonjuk.auth.application.dto.SendEmailRequestDto;
import com.kyeonjuk.auth.application.interfaces.EmailSendRepository;
import com.kyeonjuk.auth.application.interfaces.EmailVerificationRepository;
import com.kyeonjuk.auth.domain.Email;
import com.kyeonjuk.auth.domain.RandomTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    @Transactional
    public void sendEmail(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        // 이메일 인증 전송
        emailSendRepository.sendEmail(email, token);

        // DB에 저장
        emailVerificationRepository.createEmailVerification(email, token);
    }

    public void verifyEmail(String email, String token) {
        Email emailValue = Email.createEmail(email);
        emailVerificationRepository.verifyEmail(emailValue, token);
    }
}
