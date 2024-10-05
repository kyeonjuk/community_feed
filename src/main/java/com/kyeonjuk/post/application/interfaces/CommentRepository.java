package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.comment.Comment;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
