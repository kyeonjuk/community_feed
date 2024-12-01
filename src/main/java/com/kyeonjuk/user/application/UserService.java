package com.kyeonjuk.user.application;

import com.kyeonjuk.auth.application.AuthService;
import com.kyeonjuk.auth.domain.UserAuth;
import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.application.dto.GetUserResponseDto;
import com.kyeonjuk.user.application.interfaces.UserRepository;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import com.kyeonjuk.user.ui.dto.PatchProfileImageRequestDto;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        return new GetUserResponseDto(getUser(id));
    }

    @Transactional
    public void patchProfileImage(Long userId, PatchProfileImageRequestDto dto) {

        User user = getUser(userId);

        // 이미지 파일 처리
        MultipartFile profileImageUrl = dto.profileImageUrl();
        String imageUrl = saveImage(profileImageUrl);

        // 저장
        user.patchProfileImageUrl(imageUrl);
        userRepository.save(user);
    }

    public void changePassword(Long userId, String password) {

        UserAuth userAuth = authService.getUserAuth(userId);
        userAuth.changePassword(password);

        authService.patchPassword(userAuth);

    }

    public void changeProfile(Long userId, String name) {

        User user = getUser(userId);
        user.changeName(name);

        userRepository.save(user);
    }

    /*
        비슷한 이름의 유저 찾기
     */
    public List<User> findByLikeUserName(String userName) {
        return userRepository.findByNameContaining(userName);
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
