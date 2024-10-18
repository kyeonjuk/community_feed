package com.kyeonjuk.post.repository.jpa;

import com.kyeonjuk.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.content = :#{#postEntity.getContent()}, "
        + "p.state = :#{#postEntity.getState()}, "
        + "p.updDt = now() "
        + "where p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.likeCount = :#{#postEntity.getLikeCount()}, "
        + "p.updDt = now() "
        + "where p.id = :#{#postEntity.id}")
    void updateLikePostEntity(PostEntity postEntity);
}
