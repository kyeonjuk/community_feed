package com.kyeonjuk.post.domain.comment;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.CommentContent;
import com.kyeonjuk.post.domain.content.Content;
import com.kyeonjuk.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCount;

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
}
