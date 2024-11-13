package com.kyeonjuk.post.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/model/feed")
public class FeedModelController {

    @GetMapping
    public ModelAndView feedForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/image/feed");
        return modelAndView;
    }
}
