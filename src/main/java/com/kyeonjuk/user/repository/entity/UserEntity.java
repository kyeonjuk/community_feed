package com.kyeonjuk.user.repository.entity;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.common.repository.entity.TimeBaseEntity;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_user")
@AllArgsConstructor // 모든 생성자
@NoArgsConstructor  // 매개변수가 없는 생성자
@Getter
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // DB의 id 값으로 자동 증감키 설정
    private Long id;
    private String name;
    private String profileImageUrl;
    private Integer followingCount;
    private Integer followerCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followingCount = user.getFollowingCount();
        this.followerCount = user.getFollowerCount();
    }

    public User toUser() {
        return User.builder()
            .id(id)
            .info(new UserInfo(name, profileImageUrl))
            .followingCount(new PositiveIntegerCounter(followingCount))
            .followerCount(new PositiveIntegerCounter(followerCount))
            .build();
    }

}

