package com.kyeonjuk.user.application;

import com.kyeonjuk.common.domain.exception.ErrorCode;
import com.kyeonjuk.common.ui.BaseException;
import com.kyeonjuk.user.application.dto.FollowUserRequestDto;
import com.kyeonjuk.user.application.interfaces.UserRelationRepository;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.ui.dto.GetUserRelationListResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService,
        UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new BaseException(ErrorCode.ALREADY_FOLLOWING_USER);
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unFollow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new BaseException(ErrorCode.ALREADY_FOLLOWING_USER);
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }

    /*
        구독 여부 확인
     */
    public boolean isAlreadyFollow(Long userId, Long targetUserId) {

        User user = userService.getUser(userId);
        User targetUser = userService.getUser(targetUserId);

        return userRelationRepository.isAlreadyFollow(user, targetUser);
    }

    /*
        팔로워 리스트 조회
     */
    public List<GetUserRelationListResponseDto> getFollowerList(Long targetUserId) {
        return userRelationRepository.findAllByFollowerUserId(targetUserId);
    }

    /*
        팔로잉 리스트 조회
     */
    public List<GetUserRelationListResponseDto> getFollowingList(Long targetUserId) {
        return userRelationRepository.findAllByFollowingUserId(targetUserId);
    }

}
