package com.kyeonjuk.post.repository.jpa;

import com.kyeonjuk.post.repository.entity.comment.CommentEntity;
import com.kyeonjuk.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.likeCount = :#{#commentEntity.getLikeCount()}, "
        + "c.updDt = now() "
        + "where c.id = :#{#commentEntity.id}")
    void updateLikeCommentEntity(CommentEntity commentEntity);
}
