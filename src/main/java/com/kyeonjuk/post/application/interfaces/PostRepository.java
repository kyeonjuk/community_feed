package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
