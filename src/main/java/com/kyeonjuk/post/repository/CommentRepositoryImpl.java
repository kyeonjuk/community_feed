package com.kyeonjuk.post.repository;

import com.kyeonjuk.post.application.interfaces.CommentRepository;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.post.repository.entity.comment.CommentEntity;
import com.kyeonjuk.post.repository.jpa.JpaCommentRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        return jpaCommentRepository.save(commentEntity).toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return commentEntity.toComment();
    }
}
