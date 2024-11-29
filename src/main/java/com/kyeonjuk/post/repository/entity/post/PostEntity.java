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
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne                                    //foreignKey 생성 제한 (데이터 수정이 어려워서 foreignKey 를 잘 사용 X)
    @JoinColumn(name = "author_id" , foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;

    private String contentImageUrl;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    private Integer likeCount;

    @ColumnDefault("0")     //해당 컬럼을 처음 저장할 때 기본값 0으로 설정
    private int commentCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.contentImageUrl = post.getContentImageUrl();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }
    public Post toPost(){
        return Post.builder()
            .id(id)
            .author(author.toUser())
            .content(new PostContent(content))
            .contentImageUrl(contentImageUrl)
            .state(state)
            .likeCount(new PositiveIntegerCounter(likeCount))
            .build();
    }
}
