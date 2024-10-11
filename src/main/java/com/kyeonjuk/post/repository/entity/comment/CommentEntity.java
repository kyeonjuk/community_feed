package com.kyeonjuk.post.repository.entity.comment;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.common.repository.entity.TimeBaseEntity;
import com.kyeonjuk.post.domain.comment.Comment;
import com.kyeonjuk.post.domain.content.CommentContent;
import com.kyeonjuk.post.repository.entity.post.PostEntity;
import com.kyeonjuk.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity
@Table(name = "community_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private PostEntity post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  private UserEntity author;

  private String content;
  private Integer likeCount;

  public CommentEntity(Comment comment) {
    this.id = comment.getId();
    this.post = new PostEntity(comment.getPost());
    this.author = new UserEntity(comment.getAuthor());
    this.content = comment.getContent();
    this.likeCount = comment.getLikeCount();
  }

  public Comment toComment() {
    return Comment.builder()
        .id(id)
        .post(post.toPost())
        .author(author.toUser())
        .content(new CommentContent(content))
        .likeCount(new PositiveIntegerCounter(likeCount))
        .build();
  }
}
