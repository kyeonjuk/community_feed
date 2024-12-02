package com.kyeonjuk.common.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_notification")
@AllArgsConstructor // 모든 생성자
@NoArgsConstructor  // 매개변수가 없는 생성자
@Getter
public class NotificationEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String body;
    private String contentUrl;
    private boolean isRead = false;

    public NotificationEntity(Long userId, String body, String contentUrl, boolean isRead) {
        this.userId = userId;
        this.body = body;
        this.contentUrl = contentUrl;
        this.isRead = isRead;
    }

    public void updateIsRead() {
        this.isRead = true;
    }

}
