package com.kyeonjuk.post.application;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;

    public PostService(UserService userService, PostRepository postRepository,
        LikePostRepository likePostRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likePostRepository = likePostRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    /*
        유저의 게시글 리스트 가져오기
     */
    public List<Post> getMyPostList(Long userId) {
        return postRepository.findAllByUserIdOrderByIdDesc(userId);
    }

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
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(postId);
        post.updatePost(user, requestDto.content(), requestDto.state());

        return postRepository.save(post);
    }

    @Transactional
    public void likePost(LikePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if (likePostRepository.checkLike(user, post)) {
            return;
        }

        post.like(user);
        likePostRepository.like(user, post);
    }

    @Transactional
    public void unlikePost(LikePostRequestDto requestDto) {
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if (likePostRepository.checkLike(user, post)) {
            post.unLike();
            likePostRepository.unlike(user, post);
        }
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
