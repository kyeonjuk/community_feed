package com.kyeonjuk.post.application;

import com.kyeonjuk.common.application.NotificationService;
import com.kyeonjuk.common.ui.dto.SaveNotificationRequestDto;
import com.kyeonjuk.post.application.dto.CreateCommentRequestDto;
import com.kyeonjuk.post.application.dto.LikeCommentRequestDto;
import com.kyeonjuk.post.application.dto.UpdateCommentRequestDto;
import com.kyeonjuk.post.application.interfaces.CommentRepository;
import com.kyeonjuk.post.application.interfaces.LikeCommentRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final LikeCommentRepository likeCommentRepository;
    private final NotificationService notificationService;

    public Comment getComment(Long id){
        return commentRepository.findById(id);
    }

    @Transactional
    public Comment createComment(CreateCommentRequestDto requestDto){
        User user = userService.getUser(requestDto.userId());
        Post post = postService.getPost(requestDto.postId());

        //정적 생성자로 생성
        Comment comment = Comment.createComment(null,post,user,requestDto.content());

        // post 작성자에게 알림 저장 (본인글 제외)
        if (user.getId() != post.getAuthor().getId()) {
            String message = user.getName() + "님이 회원님의 글에 댓글을 작성했습니다.";
            String url = "/model/feed/postMain/" + post.getId();
            notificationService.saveNotification(new SaveNotificationRequestDto(post.getAuthorId(), message, url));
        }

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long commentId,UpdateCommentRequestDto requestDto){
        Comment comment = getComment(commentId);
        User user = userService.getUser(requestDto.userId());

        comment.updateComment(user, requestDto.content());

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        // 게시글 조회
        Comment comment = getComment(commentId);

        // 작성자 확인
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new IllegalArgumentException("작성자와 사용자가 다릅니다. 삭제할 수 없습니다.");
        }

        // 게시글 삭제
        commentRepository.delete(comment);
    }

    @Transactional
    public void deleteAllByPostId(Long postId) {

        // 게시글 삭제
        commentRepository.deleteAllByPostId(postId);
    }

    @Transactional
    public int likeComment(LikeCommentRequestDto requestDto){
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user,comment)){
            return comment.getLikeCount();
        }
        comment.like(user);
        likeCommentRepository.like(user,comment);

        // 댓글 작성자에게 알림 저장
        String message = user.getName() + "님이 회원님의 댓글에 좋아요를 눌렀습니다.";
        String url = "/model/feed/postMain/" + comment.getPost().getId();
        notificationService.saveNotification(new SaveNotificationRequestDto(comment.getAuthorId(), message, url));

        return comment.getLikeCount();
    }

    public int unlikeComment(LikeCommentRequestDto requestDto){
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user,comment)){
            comment.unLike();
            likeCommentRepository.unlike(user,comment);
            return comment.getLikeCount();
        }
        return comment.getLikeCount();
    }
}
