package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.Post;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
