package com.kyeonjuk.user.ui;

import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.application.dto.GetUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/model/user")
@RequiredArgsConstructor
public class UserModelController {

    private final UserService userService;

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/profile");

        return modelAndView;
    }

}
