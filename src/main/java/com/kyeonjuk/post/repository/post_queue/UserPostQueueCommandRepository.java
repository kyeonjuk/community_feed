package com.kyeonjuk.post.repository.post_queue;

import com.kyeonjuk.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {

    void publishPost(PostEntity postEntity);
    void saveFollowPost(Long userId, Long targetId);
    void deleteFollowPost(Long userId, Long targetId);

    // 삭제된 게시글을 큐에서 처리
    void deletePost(PostEntity postEntity);
}

