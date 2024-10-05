package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.user.domain.User;

public interface LikePostRepository {
    boolean checkLike(User user, Post post);
    void like(User user, Post post);
    void unlike(User user, Post post);
}
