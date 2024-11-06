package com.kyeonjuk.auth.domain;

import lombok.Getter;

@Getter
public class UserAuth {

    private final Email email;
    private final Password password;
    private final UserRole userRole;
    private Long userId;


    public UserAuth(String email, String password, String userRole) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptPassword(password);
        this.userRole = UserRole.valueOf(userRole);
    }

    public UserAuth(String email, String password, String userRole, Long userId) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptPassword(password);
        this.userRole = UserRole.valueOf(userRole);
        this.userId = userId;
    }

    public String getEmailText() {
        return email.getEmailText();
    }

    public String getPassword() {
        return password.getEncryptedPassword();
    }

    public String getUserRole() {
        return userRole.name();
    }
}
