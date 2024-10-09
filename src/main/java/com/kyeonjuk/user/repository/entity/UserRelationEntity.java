package com.kyeonjuk.user.repository.entity;

import com.kyeonjuk.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_user_relation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(UserRelationIdEntity.class)    // 복합키 사용 (두개 이상의 컬럼을 묶어서 하나의 기본키로 지정)
public class UserRelationEntity extends TimeBaseEntity {

    @Id
    private Long followingUserId;

    @Id
    private Long followerUserId;
}

