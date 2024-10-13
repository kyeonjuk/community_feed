package com.kyeonjuk.post.repository.entity.like;

import com.kyeonjuk.common.repository.entity.TimeBaseEntity;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId // @Embeddable로 선언한 공유 복합 속성 Entity의 id값 불러오기
    private LikeIdEntity id;

    public LikeEntity(Post post, User likeUser) {
        this.id = new LikeIdEntity(post.getId(), likeUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likeUser) {
        this.id = new LikeIdEntity(comment.getId(), likeUser.getId(), LikeTarget.COMMENT.name());
    }
}
