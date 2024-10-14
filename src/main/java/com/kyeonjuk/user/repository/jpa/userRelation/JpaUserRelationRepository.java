package com.kyeonjuk.user.repository.jpa.userRelation;

import com.kyeonjuk.user.repository.entity.UserRelationEntity;
import com.kyeonjuk.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

}
