package com.kyeonjuk.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j      // 로그 출력
@Component  // 스프링에 빈으로 등록
public class FcmConfig {

    @Value("${fcm.certification}")  // yml 파일의 변수값 가져오기
    private String fcmApplicationCredentials;

    @PostConstruct  // 스프링이 해당 빈을 생성 + 의존성 주입을 완료 한 뒤 (FcmConfig 클래스를 초기화한 후) -> 실행할 메소드
    public void initialize() throws IOException {   // firebase 설정 초기화

        // /resource 하위에 있는 해당 .json 파일을 가져옴
        ClassPathResource resource = new ClassPathResource(fcmApplicationCredentials);

        //  Firebase의 인증서 파일(.json) 을 통해 -> Google API 인증 정보를 생성
        try (InputStream is = resource.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialized!");
            }
        }
    }
}
