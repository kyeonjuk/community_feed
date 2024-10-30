package com.kyeonjuk.auth.repository;

import com.kyeonjuk.auth.application.interfaces.EmailSendRepository;
import com.kyeonjuk.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {
        // TODO
    }
}
