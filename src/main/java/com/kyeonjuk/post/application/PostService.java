package com.kyeonjuk.post.application;

import com.kyeonjuk.common.application.NotificationService;
import com.kyeonjuk.common.ui.dto.SaveNotificationRequestDto;
import com.kyeonjuk.post.application.dto.CreatePostRequestDto;
import com.kyeonjuk.post.application.dto.LikePostRequestDto;
import com.kyeonjuk.post.application.dto.UpdatePostRequestDto;
import com.kyeonjuk.post.application.interfaces.LikePostRepository;
import com.kyeonjuk.post.application.interfaces.PostRepository;
import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.domain.User;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;
    private final NotificationService notificationService;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    /*
        내가 작성한 게시글 리스트 가져오기
     */
    public List<Post> getMyPostList(Long userId) {
        return postRepository.findAllByUserIdOrderByIdDesc(userId);
    }


    @Transactional
    public Post createPost(CreatePostRequestDto requestDto) {
        // 이미지 파일 처리
        MultipartFile contentImageUrl = requestDto.contentImageUrl();
        String imageUrl = saveImage(contentImageUrl);

        User user = userService.getUser(requestDto.userId());
        PostContent content = new PostContent(requestDto.content());

        Post post = new Post(null, user, content, imageUrl, requestDto.state());

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long postId, UpdatePostRequestDto requestDto) {
        // 이미지 파일 처리 (파일이 있으면 저장)
        String imageUrl = null;
        if (requestDto.contentImageUrl() != null && !requestDto.contentImageUrl().isEmpty()) {
            imageUrl = saveImage(requestDto.contentImageUrl());
        }

        // 사용자 정보
        User user = userService.getUser(requestDto.userId());

        // 기존 게시물 가져오기
        Post post = getPost(postId);

        // 게시물 업데이트 (imageUrl을 전달)
        post.updatePost(user, requestDto.content(), requestDto.state(), imageUrl);

        // 게시물 저장
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, Long userId) {
        // 게시글 조회
        Post post = getPost(postId);

        // 작성자 확인
        if (!post.getAuthor().getId().equals(userId)) {
            throw new IllegalArgumentException("작성자와 사용자가 다릅니다. 삭제할 수 없습니다.");
        }

        // 게시글 삭제
        postRepository.delete(post);
    }


    @Transactional
    public int likePost(LikePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if (likePostRepository.checkLike(user, post)) {
            return post.getLikeCount();
        }

        post.like(user);
        likePostRepository.like(user, post);

        // 작성자 알림 저장
        String message = user.getName() + "님이 회원님의 글에 좋아요를 눌렀습니다.";
        String url = "/model/feed/postMain/" + post.getId();
        notificationService.saveNotification(new SaveNotificationRequestDto(post.getAuthorId(), message, url));

        return post.getLikeCount();
    }

    @Transactional
    public int unlikePost(LikePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if (likePostRepository.checkLike(user, post)) {
            post.unlike();
            likePostRepository.unlike(user, post);
        }

        return post.getLikeCount();
    }

    /*
         이미지 파일 저장
      */
    private String saveImage(MultipartFile image) {
        // 프로젝트 루트 디렉토리 + "uploads/images" 폴더 경로 생성
        String uploadDirectory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "uploads" + File.separator + "images";

        // 디렉토리가 없으면 생성
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            boolean created = directory.mkdirs(); // 디렉토리 생성
            if (!created) {
                throw new RuntimeException("디렉토리 생성 실패: " + uploadDirectory);
            }
        }

        try {
            // 파일 이름 추출
            String originalFileName = image.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                throw new RuntimeException("파일 이름이 잘못되었습니다.");
            }

            // UUID를 사용해 고유한 파일 이름 생성
            String uniqueFileName = generateUniqueFileName(originalFileName);
            String imagePath = uploadDirectory + File.separator + uniqueFileName;

            // 파일을 지정된 경로에 저장
            File file = new File(imagePath);
            image.transferTo(file); // 파일 저장

            return uniqueFileName; // 저장된 파일 경로 반환
        } catch (IOException e) {
            e.printStackTrace();  // 예외 로그
            throw new RuntimeException("이미지 저장 실패: " + e.getMessage(), e);
        }
    }

    // 고유한 파일 이름 생성 (UUID 사용)
    private String generateUniqueFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + extension; // UUID로 고유 이름 생성
        return uniqueFileName;
    }
}