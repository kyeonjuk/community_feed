package com.kyeonjuk.auth.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/model/auth")
public class AuthModelController {

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

}
