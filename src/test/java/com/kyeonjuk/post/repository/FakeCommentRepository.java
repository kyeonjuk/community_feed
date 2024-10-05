package com.kyeonjuk.post.repository;

import com.kyeonjuk.post.application.interfaces.CommentRepository;
import com.kyeonjuk.post.domain.comment.Comment;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {

    private final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            store.put(comment.getId(), comment);
        }

        long commentId = store.size() + 1;
        Comment newComment
            = new Comment(commentId, comment.getPost(), comment.getAuthor(), comment.getContentObject());
        store.put(commentId, newComment);

        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
