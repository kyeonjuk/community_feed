package com.kyeonjuk.post.domain;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;

    public Post(Long id, User author, PostContent content) {

        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
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
}
