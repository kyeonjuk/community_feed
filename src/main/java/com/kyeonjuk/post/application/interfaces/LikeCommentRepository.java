package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.user.domain.User;

public interface LikeCommentRepository {

    boolean checkLike(User user, Comment comment);

    void like(User user, Comment comment);

    void unlike(User user, Comment comment);
}
