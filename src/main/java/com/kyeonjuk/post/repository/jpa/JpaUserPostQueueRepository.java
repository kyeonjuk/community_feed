package com.kyeonjuk.post.repository.jpa;

import com.kyeonjuk.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long>{

    void deleteAllByUserIdAndAuthorId(Long userId, Long authorId);

    void deleteByUserIdAndPostId(Long userId, Long postId);
}
