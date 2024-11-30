package com.kyeonjuk.user.application.interfaces;

import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.ui.dto.GetUserRelationListResponseDto;
import java.util.List;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);

    void save(User user, User targetUser);

    void delete(User user, User targetUser);

    List<GetUserRelationListResponseDto> findAllByFollowingUserId(Long followingUserId);

    List<GetUserRelationListResponseDto> findAllByFollowerUserId(Long followerUserId);
}
