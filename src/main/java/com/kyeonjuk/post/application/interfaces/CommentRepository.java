package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);
    void delete(Comment comment);
    void deleteAllByPostId(Long  postId);
}
