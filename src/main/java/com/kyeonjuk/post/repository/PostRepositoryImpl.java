package com.kyeonjuk.post.repository;

import com.kyeonjuk.post.application.interfaces.PostRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.repository.entity.post.PostEntity;
import com.kyeonjuk.post.repository.jpa.JpaPostRepository;
import com.kyeonjuk.post.repository.post_queue.UserPostQueueCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);

        if (postEntity.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }

        // 저장 + 수정
        postEntity = jpaPostRepository.save(postEntity);
        userPostQueueCommandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return postEntity.toPost();
    }

    @Override
    public List<Post> findAllByUserIdOrderByIdDesc(Long userId) {
        List<PostEntity> postEntityList = jpaPostRepository.findAllByAuthorIdOrderByIdDesc(userId);
        return postEntityList.stream().map(PostEntity::toPost).toList();
    }
}
