package com.kyeonjuk.user.repository.jpa;

import com.kyeonjuk.user.application.dto.GetUserListResponseDto;
import com.kyeonjuk.user.repository.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value =
        "SELECT new com.kyeonjuk.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
            + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value =
        "SELECT new com.kyeonjuk.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
            + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
