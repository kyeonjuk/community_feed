package com.kyeonjuk.post.repository.jpa;

import com.kyeonjuk.post.repository.entity.post.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT p FROM PostEntity p "
        + "where p.author.id = :authorId")
    List<PostEntity> findFollowingPosts(Long authorId);

    List<PostEntity> findAllByAuthorId(Long authorId);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.content = :#{#postEntity.getContent()}, "
        + "p.state = :#{#postEntity.getState()}, "
        + "p.updDt = now() "
        + "where p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.likeCount = p.likeCount + :likeCount, "
        + "p.updDt = now() "
        + "where p.id = :postId")
    void updateLikePostEntity(Long postId, Integer likeCount);

    @Modifying
    @Query(value = "UPDATE PostEntity p "
        + "SET p.commentCount = p.commentCount + 1, "
        + "p.updDt = now() "
        + "where p.id = :id")
    void increaseCommentCountEntity(Long id);
}
