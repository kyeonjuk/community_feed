package com.kyeonjuk.post.application.interfaces;

import com.kyeonjuk.post.domain.Post;
import java.util.List;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);

    void delete(Post post);

    List<Post> findAllByUserIdOrderByIdDesc(Long userId);
}
