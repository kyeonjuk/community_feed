package com.kyeonjuk.post.repository.post_queue;

import com.kyeonjuk.post.repository.entity.post.PostEntity;
import com.kyeonjuk.post.repository.entity.post.UserPostQueueEntity;
import com.kyeonjuk.post.repository.jpa.JpaUserPostQueueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!test"})
@RequiredArgsConstructor
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {

    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    /*
        [포스트 작성시 배포] 팔로우 한 유저에게 -> 피드 추가
     */
    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {
        for (Long userId: userIdList) {

            UserPostQueueEntity userPostQueueEntity = new UserPostQueueEntity(
                userId,
                postEntity.getId(),
                postEntity.getAuthor().getId()
            );

            jpaUserPostQueueRepository.save(userPostQueueEntity);
        }
    }

    /*
        [팔로우 시 기존 포스트 배포]
     */
    @Override
    public void publishPostToFollowerUserList(List<PostEntity> postEntities, Long userId) {
        for (PostEntity postEntity : postEntities) {
            UserPostQueueEntity userPostQueueEntity = new UserPostQueueEntity(
                userId,
                postEntity.getId(),
                postEntity.getAuthor().getId()
            );

            jpaUserPostQueueRepository.save(userPostQueueEntity);
        }
    }

    @Override
    public void deleteFeed(Long userId, Long targetUserId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetUserId);
    }


    @Override
    public void deletePostFromFollowingUserList(Long postId, List<Long> userIdList) {
        for (Long userId : userIdList) {
            jpaUserPostQueueRepository.deleteByUserIdAndPostId(userId, postId);
        }
    }

}
