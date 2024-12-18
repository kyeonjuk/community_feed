package com.kyeonjuk.auth.ui;

import com.kyeonjuk.auth.application.AuthService;
import com.kyeonjuk.auth.application.EmailService;
import com.kyeonjuk.auth.application.dto.CreateUserAuthRequestDto;
import com.kyeonjuk.auth.application.dto.SendEmailRequestDto;
import com.kyeonjuk.auth.ui.dto.VerifyEmailResponseDto;
import com.kyeonjuk.common.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto) {
        emailService.sendEmail(dto);
        return Response.ok(null);
    }

    @PostMapping("/verify-token")
    public Response<Void> verifyEmail(@RequestBody VerifyEmailResponseDto dto) {
        emailService.verifyEmail(dto.getEmail(), dto.getToken());

        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<Long> register(@RequestBody CreateUserAuthRequestDto dto) {
        Long id = authService.registerUser(dto);
        return Response.ok(id);
    }
}
