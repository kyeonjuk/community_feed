package com.kyeonjuk.auth.application.interfaces;

import com.kyeonjuk.auth.domain.Email;

public interface EmailVerificationRepository {
    void createEmailVerification(Email email, String token);
}
