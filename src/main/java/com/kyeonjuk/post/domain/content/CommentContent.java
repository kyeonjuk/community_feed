package com.kyeonjuk.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_POST_LENGTH = 100;

    public CommentContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (contentText.length() > MAX_POST_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}