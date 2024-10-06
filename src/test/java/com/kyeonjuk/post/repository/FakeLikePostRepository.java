package com.kyeonjuk.post.repository;

import com.kyeonjuk.post.application.interfaces.LikePostRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.user.domain.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FakeLikePostRepository implements LikePostRepository {

    private final Map<Post, Set<User>> store = new HashMap<>();

    @Override
    public boolean checkLike(User user, Post post) {
        if (store.get(post) == null) {
            return false;
        }

        return store.get(post).contains(user);
    }

    @Override
    public void like(User user, Post post) {
        Set<User> users = store.get(post);

        if (users == null) {
            users = new HashSet<>();
        }

        users.add(user);
        store.put(post, users);
    }

    @Override
    public void unlike(User user, Post post) {
        Set<User> users = store.get(post);

        if (users == null) {
            return;
        }

        users.remove(user);
        store.put(post, users);
    }
}
