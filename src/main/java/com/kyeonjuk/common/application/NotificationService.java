package com.kyeonjuk.common.application;

import com.kyeonjuk.common.domain.exception.ErrorCode;
import com.kyeonjuk.common.repository.NotificationRepository;
import com.kyeonjuk.common.repository.entity.NotificationEntity;
import com.kyeonjuk.common.ui.BaseException;
import com.kyeonjuk.common.ui.dto.SaveNotificationRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // 알림 저장
    @Transactional
    public void saveNotification(SaveNotificationRequestDto dto) {
        NotificationEntity entity = new NotificationEntity(dto.userId(), dto.body(), dto.contentUrl(), false);
        notificationRepository.save(entity);
    }

    // 읽지 않은 내 알림 조회
    public List<NotificationEntity> getAllMyNotificationsUnread(Long userId) {
        return notificationRepository.findAllByUserIdAndIsReadFalse(userId);
    }

    // 알림 읽음 처리
    @Transactional
    public void markAsRead(Long id) {
        NotificationEntity entity = findById(id);
        entity.updateIsRead();

        notificationRepository.save(entity);
    }

    // 알림 조회
    public NotificationEntity findById(Long id) {
        NotificationEntity entity = notificationRepository.findById(id)
            .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        return entity;
    }

    // 읽지않은 알림 개수 조회
    public Long getUnreadNotificationCount(Long userId) {
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

}
