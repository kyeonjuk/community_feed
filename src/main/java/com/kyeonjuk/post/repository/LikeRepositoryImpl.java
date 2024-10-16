package com.kyeonjuk.post.repository;

import com.kyeonjuk.post.application.interfaces.LikeCommentRepository;
import com.kyeonjuk.post.application.interfaces.LikePostRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.post.repository.entity.like.LikeEntity;
import com.kyeonjuk.post.repository.entity.like.LikeIdEntity;
import com.kyeonjuk.post.repository.jpa.JpaLikeRepository;
import com.kyeonjuk.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeCommentRepository, LikePostRepository {

    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public void like(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.save(likeEntity);
    }

    @Override
    public void unlike(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
    }

    @Override
    public boolean checkLike(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public void like(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.save(likeEntity);
    }

    @Override
    public void unlike(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
    }
}
