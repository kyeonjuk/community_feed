package com.kyeonjuk.auth.application.interfaces;

import com.kyeonjuk.auth.domain.Email;

public interface EmailSendRepository {
    void sendEmail(Email email, String token);
}
