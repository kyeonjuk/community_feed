package com.kyeonjuk.post.application;

import com.kyeonjuk.post.application.dto.CreateCommentRequestDto;
import com.kyeonjuk.post.application.dto.LikeCommentRequestDto;
import com.kyeonjuk.post.application.dto.UpdateCommentRequestDto;
import com.kyeonjuk.post.application.interfaces.CommentRepository;
import com.kyeonjuk.post.application.interfaces.LikeCommentRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final LikeCommentRepository likeCommentRepository;

    public CommentService(UserService userService, CommentRepository commentRepository,
        PostService postService, LikeCommentRepository likeCommentRepository) {
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.likeCommentRepository = likeCommentRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = postService.getPost(requestDto.postId());

        //정적 생성자로 생성
        Comment comment = Comment.createComment(null, post, user, requestDto.content());

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long commentId, UpdateCommentRequestDto requestDto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(requestDto.userId());

        comment.updateComment(user, requestDto.content());

        return commentRepository.save(comment);
    }

    @Transactional
    public void likeComment(LikeCommentRequestDto requestDto) {
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user, comment)) {
            return;
        }
        comment.like(user);
        likeCommentRepository.like(user, comment);
    }

    @Transactional
    public void unlikeComment(LikeCommentRequestDto requestDto) {
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user, comment)) {
            comment.unLike();
            likeCommentRepository.unlike(user, comment);
        }
    }
}
