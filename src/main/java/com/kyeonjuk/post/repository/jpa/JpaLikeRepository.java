package com.kyeonjuk.post.repository.jpa;

import com.kyeonjuk.post.repository.entity.like.LikeEntity;
import com.kyeonjuk.post.repository.entity.like.LikeIdEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

    boolean existsById(LikeIdEntity likeIdEntity);
}
