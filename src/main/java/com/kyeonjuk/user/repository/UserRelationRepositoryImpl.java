package com.kyeonjuk.user.repository;

import com.kyeonjuk.post.repository.post_queue.UserPostQueueCommandRepository;
import com.kyeonjuk.user.application.interfaces.UserRelationRepository;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.repository.entity.UserEntity;
import com.kyeonjuk.user.repository.entity.UserRelationEntity;
import com.kyeonjuk.user.repository.entity.UserRelationIdEntity;
import com.kyeonjuk.user.repository.jpa.user.JpaUserRepository;
import com.kyeonjuk.user.repository.jpa.userRelation.JpaUserRelationRepository;
import com.kyeonjuk.user.ui.dto.GetUserRelationListResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity userRelation = new UserRelationEntity(user.getId(), targetUser.getId());
        // 저장 + 팔로우 count 증가
        jpaUserRelationRepository.save(userRelation);

        // 변화된 팔로우값 update
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));

        // 팔로우의 post를 피드에 저장
        userPostQueueCommandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity userRelationId = new UserRelationIdEntity(user.getId(), targetUser.getId());
        // 삭제 + 팔로우 count 감소
        jpaUserRelationRepository.deleteById(userRelationId);

        // 변화된 팔로우값 update
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));

        // 언팔로우의 post를 피드에서 삭제
        userPostQueueCommandRepository.deleteFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    public List<GetUserRelationListResponseDto> findAllByFollowingUserId(Long followingUserId) {

        List<Long> userIdList = jpaUserRelationRepository.findAllByFollowingUserId(followingUserId);

        List<User> userEntityList = jpaUserRepository.findAllByIdIn(userIdList)
            .stream()
            .map(UserEntity::toUser).toList();

        List<GetUserRelationListResponseDto> resultList = new ArrayList<>();

        for (User user : userEntityList) {
            resultList.add(
                new GetUserRelationListResponseDto(user.getName(), user.getProfileImageUrl(), user.getId())
            );
        }

        return resultList;
    }

    @Override
    public List<GetUserRelationListResponseDto> findAllByFollowerUserId(Long followerUserId) {
        List<Long> userIdList = jpaUserRelationRepository.findAllByFollowerUserId(followerUserId);

        List<User> userEntityList = jpaUserRepository.findAllByIdIn(userIdList)
            .stream()
            .map(UserEntity::toUser).toList();

        List<GetUserRelationListResponseDto> resultList = new ArrayList<>();

        for (User user : userEntityList) {
            resultList.add(
                new GetUserRelationListResponseDto(user.getName(), user.getProfileImageUrl(), user.getId())
            );
        }

        return resultList;
    }
}
