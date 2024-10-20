package com.kyeonjuk.post.repository.entity.post;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.common.repository.entity.TimeBaseEntity;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne                                        // 앞의 Many = 현재 Entity(Post) [1:N]
    @JoinColumn(name = "author_id",                   // 아래의 Entity의 id값으로 join
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))  // DDL 생성시에 외래키 생성 제한
    private UserEntity author;

    private String content;

    @Convert(converter = PostPublicationStateConverter.class)   // Enum값을 DB에 저장하기위한 타입변환 기능
    private PostPublicationState state;

    private Integer likeCount;

    @ColumnDefault("0")     // 해당 컬럼을 처음 저장할 때 기본값 0으로 설정
    private int commentCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    // DTO객체로 변환
    public Post toPost() {
        return Post.builder()
            .id(id)
            .author(author.toUser())
            .content(new PostContent(content))
            .state(state)
            .likeCount(new PositiveIntegerCounter(likeCount))
            .build();
    }
}
