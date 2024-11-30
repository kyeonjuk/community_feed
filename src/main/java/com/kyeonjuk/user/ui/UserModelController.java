package com.kyeonjuk.user.ui;

import com.kyeonjuk.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/model/user")
@RequiredArgsConstructor
public class UserModelController {

    private final UserService userService;

    @GetMapping("/profile/{otherUserId}")
    public ModelAndView profile(@PathVariable(name = "otherUserId") Long otherUserId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/profile");

        modelAndView.addObject("otherUserId", otherUserId);

        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/profile");

        modelAndView.addObject("otherUserId", 0L);

        return modelAndView;
    }

    @GetMapping("/profile/uploadData")
    public ModelAndView uploadProfileData() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/profileUpdate");

        return modelAndView;
    }

    @GetMapping("/profile/uploadImage")
    public ModelAndView uploadProfileImage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("image/upload");

        return modelAndView;
    }

}
