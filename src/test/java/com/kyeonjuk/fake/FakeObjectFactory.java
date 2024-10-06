package com.kyeonjuk.fake;

import com.kyeonjuk.post.application.CommentService;
import com.kyeonjuk.post.application.PostService;
import com.kyeonjuk.post.application.interfaces.CommentRepository;
import com.kyeonjuk.post.application.interfaces.LikeCommentRepository;
import com.kyeonjuk.post.application.interfaces.LikePostRepository;
import com.kyeonjuk.post.application.interfaces.PostRepository;
import com.kyeonjuk.post.repository.FakeCommentRepository;
import com.kyeonjuk.post.repository.FakeLikePostRepository;
import com.kyeonjuk.post.repository.FakeLikeCommentRepository;
import com.kyeonjuk.post.repository.FakePostRepository;
import com.kyeonjuk.user.application.UserRelationService;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.application.interfaces.UserRelationRepository;
import com.kyeonjuk.user.application.interfaces.UserRepository;
import com.kyeonjuk.user.repository.FakeUserRelationRepository;
import com.kyeonjuk.user.repository.FakeUserRepository;

// 싱글톤으로 fake 객체 생성
public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository
        = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikePostRepository fakeLikePostRepository = new FakeLikePostRepository();
    private static final LikeCommentRepository fakeLikeCommentRepository = new FakeLikeCommentRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikePostRepository);
    private static final CommentService commentService = new CommentService(userService, fakeCommentRepository, postService, fakeLikeCommentRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
