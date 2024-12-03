package com.kyeonjuk.user.ui.dto;

import com.kyeonjuk.common.repository.entity.NotificationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUnreadNotificationResponseDto {

    private Long id;
    private Long userId;
    private String body;
    private String contentUrl;
    private boolean isRead;

    public void toDto(NotificationEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.body = entity.getBody();
        this.contentUrl = entity.getContentUrl();
        this.isRead = entity.isRead();
    }
}
