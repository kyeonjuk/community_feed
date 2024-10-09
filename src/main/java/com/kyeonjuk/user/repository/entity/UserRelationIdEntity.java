package com.kyeonjuk.user.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable         // (JPA) 복합키를 사용할 때 작성 (다른 Entity 에 내장되는 클래스)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserRelationIdEntity {
    private Long followingUserId;
    private Long followerUserId;
}
