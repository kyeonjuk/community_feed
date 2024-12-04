package com.kyeonjuk.sampleData;

import com.kyeonjuk.auth.application.interfaces.UserAuthRepository;
import com.kyeonjuk.auth.domain.UserAuth;
import com.kyeonjuk.post.application.CommentService;
import com.kyeonjuk.post.application.dto.CreateCommentRequestDto;
import com.kyeonjuk.post.application.interfaces.PostRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.user.application.UserRelationService;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.application.dto.FollowUserRequestDto;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final UserAuthRepository userAuthRepository;
    private final UserRelationService userRelationService;
    private final PostRepository postRepository;
    private final CommentService commentService;
    private final UserService userService;

    private static final boolean LOAD_SAMPLE_DATA = false; // false로 설정하면 샘플 데이터가 로드되지 않음

    @Override
    public void run(String... args) throws Exception {
        if (LOAD_SAMPLE_DATA) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // 1. 회원 데이터 11개
        List<String> nameList = new ArrayList<>(Arrays.asList
            ("유재석", "지석진", "김종국", "하하", "송지효", "이광수", "전소민", "양세찬", "박명수", "정형돈", "노홍철"));

        for (int i = 1; i <= 11; i++) {
            UserAuth userAuth = new UserAuth("user" + i + "@test.com", "1111", "USER");
            UserInfo userInfo = new UserInfo(nameList.get(i-1), "user" + i + ".png");
            User user = new User(null, userInfo);
            userAuthRepository.registerUser(userAuth, user);
        }

        // 2. 팔로우, 팔로워 관계 등록 (나빼고 다른 사람 모두 팔로우)
        for (int i = 1; i <= 11; i++) {     // userId
            for (int j = 1; j <= 11; j++) {  // targetUserId
                if (i != j) {
                    FollowUserRequestDto followUserRequestDto = new FollowUserRequestDto(Long.valueOf(i), Long.valueOf(j));
                    userRelationService.follow(followUserRequestDto);
                }
            }
        }

        // 3. 포스트 등록 (user 당 포스트 5개 작성)
        for (int i = 1; i <= 11; i++) {
            for (int j = 1; j <= 5; j++) {
                PostContent postContent = new PostContent("오늘의 글입니다.");
                Post post = new Post(null, userService.getUser(Long.valueOf(i)), postContent, "post" + j + ".png", PostPublicationState.PUBLIC);
                postRepository.save(post);
            }
        }

        // 4. 댓글 작성
        for (int i = 1; i <= 11; i++) {
            for (int j = 1; j <= 55; j++) {
                CreateCommentRequestDto dto = new CreateCommentRequestDto(Long.valueOf(j), Long.valueOf(i), "댓글입니다.");
                commentService.createComment(dto);
            }
        }



    }
}
