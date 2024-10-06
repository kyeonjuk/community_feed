package com.kyeonjuk.post.domain;

import com.kyeonjuk.common.domain.PositiveIntegerCounter;
import com.kyeonjuk.post.domain.common.DateTimeInfo;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    // 정적 생성자 1
    public static Post createPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    // 정적 생성자 2
    public static Post createDefaultPost(Long id, User author, String content) {
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, PostContent content) {

        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public Post(Long id, User author, PostContent content, PostPublicationState state) {

        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
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

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(updateContent);
        this.state = state;
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }

    public DateTimeInfo getDateTimeInfo() {
        return content.getDateTimeInfo();
    }

    public PostPublicationState getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Long getAuthorId() {
        return author.getId();
    }

    public PostContent getContentObject() {
        return content;
    }
}
