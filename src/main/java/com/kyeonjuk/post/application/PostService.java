package com.kyeonjuk.post.application;

import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.application.dto.LikePostRequestDto;
import com.kyeonjuk.post.application.dto.UpdatePostRequestDto;
import com.kyeonjuk.post.application.interfaces.LikePostRepository;
import com.kyeonjuk.post.application.interfaces.PostRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;

    public PostService(UserService userService, PostRepository postRepository,
        LikePostRepository likePostRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likePostRepository = likePostRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        PostContent content = new PostContent(requestDto.content());

        Post post = new Post(null, user, content, requestDto.state());

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long postId, UpdatePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(postId);
        post.updatePost(user, requestDto.content(), requestDto.state());

        return postRepository.save(post);
    }

    @Transactional
    public void likePost(LikePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if (likePostRepository.checkLike(user, post)) {
            return;
        }

        post.like(user);
        likePostRepository.like(user, post);
    }

    @Transactional
    public void unlikePost(LikePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if (likePostRepository.checkLike(user, post)) {
            post.unLike();
            likePostRepository.unlike(user, post);
        }
    }
}
