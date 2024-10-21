package com.kyeonjuk.post.domain.comment;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.CommentContent;
import com.kyeonjuk.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCount;

    // 정적 생성자
    public static Comment createComment(Long id, Post post, User user, String content) {
        return new Comment(id, post, user, new CommentContent(content));
    }

    public Comment(Long id, Post post, User author, CommentContent content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (post == null) {
            throw new IllegalArgumentException();
        }

        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unLike() {
        likeCount.decrease();
    }

    public void updateComment(User user, String updateContent) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
    }

    public String getContent() {
        return content.getContentText();
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public Long getPostId() {
        return post.getId();
    }

    public Long getAuthorId() {
        return author.getId();
    }

    public CommentContent getContentObject() {
        return content;
    }
}
