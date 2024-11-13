package com.kyeonjuk.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.kyeonjuk.message.application.interfaces.MessageRepository;
import com.kyeonjuk.message.repository.entity.FcmTokenEntity;
import com.kyeonjuk.user.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

// FCM 을 사용하여 특정 사용자에게 좋아요 알림 메시지를 보내는 기능
@Repository
@RequiredArgsConstructor
public class FcmMessageRepositoryImpl implements MessageRepository {

    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s님 글에 좋아요를 눌렀습니다.";
    private static final String MESSAGE_KEY = "message";

    @Override
    public void sendLikeMessage(User sender, User targetUser) {

        // 1) 알림을 받을 user의 fcm 토큰값 가져오기
        Optional<FcmTokenEntity> tokenEntity = jpaFcmTokenRepository.findById(targetUser.getId());

        if (tokenEntity.isEmpty()) {    // fcm 토큰이 없을 때
            return;
        }

        FcmTokenEntity token = tokenEntity.get();   // Optional 벗기기

        // 2) 알림 메시지 생성
        //  ㄴ +) <import> Firebase 의 Message 객체
        Message message = Message.builder()
            .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sender.getName(), targetUser.getName()))
            .setToken(token.getFcmToken())  // 메시지 전송할 대상 (수신자)
            .build();

        // 4) 비동기로 알림 전송
        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
