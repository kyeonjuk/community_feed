package com.kyeonjuk.post.repository;

import com.kyeonjuk.post.application.interfaces.CommentRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.post.repository.entity.comment.CommentEntity;
import com.kyeonjuk.post.repository.entity.post.PostEntity;
import com.kyeonjuk.post.repository.jpa.JpaCommentRepository;
import com.kyeonjuk.post.repository.jpa.JpaPostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository{

    private final JpaCommentRepository jpaCommentRepository;

    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post post = comment.getPost();  //해당 Post 의 commentCount 값 1 증가
        CommentEntity entity = new CommentEntity(comment);
        entity = jpaCommentRepository.save(entity);
        jpaPostRepository.increaseCommentCount(post.getId());
        return entity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity entity = jpaCommentRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException :: new);
        return entity.toComment();

    }

    @Override
    public void delete(Comment comment) {

        CommentEntity entity = new CommentEntity(comment);

        jpaCommentRepository.delete(entity);

    }

    @Override
    public void deleteAllByPostId(Long postId) {


        jpaCommentRepository.deleteAllByPostId(postId);

    }

}

