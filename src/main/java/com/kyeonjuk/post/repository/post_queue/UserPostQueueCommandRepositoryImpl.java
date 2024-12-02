package com.kyeonjuk.post.repository.post_queue;

import com.kyeonjuk.post.repository.entity.post.PostEntity;
import com.kyeonjuk.post.repository.entity.post.UserPostQueueEntity;
import com.kyeonjuk.post.repository.jpa.JpaPostRepository;
import com.kyeonjuk.post.repository.jpa.JpaUserPostQueueRepository;
import com.kyeonjuk.user.repository.entity.UserEntity;
import com.kyeonjuk.user.repository.jpa.userRelation.JpaUserRelationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final UserQueueRedisRepository userQueueRedisRepository;

    // post가 등록될 때 작성자를 팔로우하는 유저 피드에 해당 포스트 등록
    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        //작성자 정보 가져오기
        UserEntity author = postEntity.getAuthor();

        //작성자를 팔로우하는 유저 정보 가져오기
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(author.getId());

        userQueueRedisRepository.publishPostToFollowingUserList(postEntity,followerIds);


    }

    // 팔로우한 작성자에 post를 전부 피드에 등록
    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        //작성자의 post 가져오기
        List<PostEntity> followingPosts= jpaPostRepository.findFollowingPosts(targetId);

        userQueueRedisRepository.publishPostToFollowerUserList(followingPosts,userId);

    }
    // 언팔로우 작성자에 post를 전부 삭제
    @Override
    @Transactional
    public void deleteFollowPost(Long userId, Long targetId) {

        userQueueRedisRepository.deleteFeed(userId, targetId);
    }

    @Override
    @Transactional
    public void deletePost(PostEntity postEntity) {
        // 작성자 정보 가져오기
        UserEntity author = postEntity.getAuthor();

        // 작성자를 팔로우하는 유저 ID 목록 가져오기
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(author.getId());

        // 팔로워들의 피드에서 해당 게시글 삭제
        userQueueRedisRepository.deletePostFromFollowingUserList(postEntity.getId(), followerIds);

        System.out.println("Deleted post from all followers' feeds: " + postEntity.getId());
    }

}

