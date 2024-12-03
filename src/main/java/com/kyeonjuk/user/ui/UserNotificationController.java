package com.kyeonjuk.user.ui;

import com.kyeonjuk.common.application.NotificationService;
import com.kyeonjuk.common.principal.AuthPrincipal;
import com.kyeonjuk.common.principal.UserPrincipal;
import com.kyeonjuk.common.repository.entity.NotificationEntity;
import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.user.ui.dto.GetUnreadNotificationResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/notification")
@RequiredArgsConstructor
public class UserNotificationController {

    private final NotificationService notificationService;

    /*
        읽지 않은 알림 개수 조회
     */
    @GetMapping("/getUnreadCount")
    public Response<Long> getUnreadCount(@AuthPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getUserId();

        Long count = notificationService.getUnreadNotificationCount(userId);

        return Response.ok(count);
    }

    /*
        읽지 않은 알림 내용 조회
     */
    @GetMapping("/getUnread")
    public Response<List<GetUnreadNotificationResponseDto>> getUnread(@AuthPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getUserId();

        // 읽지않은 알림 조회
        List<NotificationEntity> resultList = notificationService.getAllMyNotificationsUnread(userId);

        // -> dto
        List<GetUnreadNotificationResponseDto> dto = resultList.stream().map(NotificationEntity::toDto).toList();

        return Response.ok(dto);
    }
}
