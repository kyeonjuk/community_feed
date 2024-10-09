package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.comment.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
