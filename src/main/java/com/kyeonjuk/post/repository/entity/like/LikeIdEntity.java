package com.kyeonjuk.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Embeddable         // (JPA) Entity의 속성으로 사용할 수 있는 클래스 정의 (공유 복합 속성)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LikeIdEntity {

    // 3개의 복합키!
    private Long targetId;
    private Long userId;
    private String targetType;
}
