package com.kyeonjuk.post.ui;

import com.kyeonjuk.post.application.PostService;
import com.kyeonjuk.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
@RequestMapping("/model/feed")
public class FeedModelController {

    private final PostService postService;

    @GetMapping
    public ModelAndView feedForm(){

        ModelAndView modelAndView =new ModelAndView();

        modelAndView.setViewName("image/feed");
        return modelAndView;
    }
    @GetMapping("/post")
    public ModelAndView createPostForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("image/uploadPost");
        return modelAndView;
    }

    @GetMapping("/postMain/{postId}")
    public ModelAndView postMain(@PathVariable Long postId) {
        ModelAndView modelAndView = new ModelAndView();

        Post post = postService.getPost(postId);
        modelAndView.addObject("post", post); // 모델에 게시물 데이터 추가

        modelAndView.setViewName("image/postMain"); // postMain.html을 렌더링
        return modelAndView;
    }

}