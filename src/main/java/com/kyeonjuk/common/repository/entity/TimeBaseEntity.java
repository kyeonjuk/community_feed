package com.kyeonjuk.common.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)  // Entity 의 생성 및 수정 시점을 자동으로 기록
@MappedSuperclass                               // 자식클래스에게 공통 속성 제공 (부모(현재)클래스는 테이블 매핑X)
@Getter
public class TimeBaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    private LocalDateTime updDt;
}


