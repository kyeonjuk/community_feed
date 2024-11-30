package com.kyeonjuk.post.domain.content;

import com.kyeonjuk.common.domain.exception.ErrorCode;
import com.kyeonjuk.common.ui.BaseException;

public class PostContent extends Content {

    private static final int MIN_POST_LENGTH = 5;
    private static final int MAX_POST_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }


    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new BaseException(ErrorCode.EMPTY_POST_CONTENT);
        }

        if (contentText.length() > MAX_POST_LENGTH) {
            throw new BaseException(ErrorCode.BELOW_MIN_POST_LENGTH);
        }

        if (contentText.length() < MIN_POST_LENGTH) {
            throw new BaseException(ErrorCode.EXCEEDED_MAX_POST_LENGTH);
        }
    }
}