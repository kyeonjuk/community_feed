package com.kyeonjuk.user.repository.jpa.userRelation;

import com.kyeonjuk.user.repository.entity.UserRelationEntity;
import com.kyeonjuk.user.repository.entity.UserRelationIdEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    @Query("SELECT u.followingUserId FROM UserRelationEntity u "
        + "where u.followerUserId = :authorId")
    List<Long> findFollowers(Long authorId);
}
