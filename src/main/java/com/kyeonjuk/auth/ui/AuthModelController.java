package com.kyeonjuk.auth.ui;

import com.kyeonjuk.auth.application.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/model/auth")
@RequiredArgsConstructor
public class AuthModelController {

    private final AuthService authService;

    @GetMapping("/register")
    public ModelAndView registerForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/signup");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/signin");
        return modelAndView;
    }

    @GetMapping("/logout/{userId}")
    public ModelAndView logout(@PathVariable Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/signin");

        // FCM 토큰 삭제
        authService.deleteFcmToken(userId);

        return modelAndView;
    }
}
