package com.kyeonjuk.post.repository;

import com.kyeonjuk.post.repository.entity.post.PostEntity;
import com.kyeonjuk.post.repository.post_queue.UserPostQueueQueryRepository;
import com.kyeonjuk.post.ui.dto.GetContentResponseDto;
import com.kyeonjuk.post.ui.dto.GetPostContentResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

    public FakeUserPostQueryRepository(FakeUserQueueRedisRepository fakeUserQueueRedisRepository) {
        this.fakeUserQueueRedisRepository = fakeUserQueueRedisRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {

        List<PostEntity> postEntityList = fakeUserQueueRedisRepository.getPostListByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .build();

            result.add(res);
        }
        return result;
    }

    @Override
    public List<GetContentResponseDto> getCommentResponse(Long postId, Long lastCommentId,
        Long userId) {
        return List.of();
    }
}
