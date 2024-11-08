package com.kyeonjuk.auth.ui;

import com.kyeonjuk.auth.application.AuthService;
import com.kyeonjuk.auth.application.dto.LoginRequestDto;
import com.kyeonjuk.auth.application.dto.UserAccessTokenResponseDto;
import com.kyeonjuk.common.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return Response.ok(authService.login(dto));
    }

}
