package com.kyeonjuk.message.application.interfaces;

import com.kyeonjuk.user.domain.User;

// 좋아요를 눌렀을 때 메시지 전송
public interface MessageRepository {

    void sendLikeMessage(User sender, User targetUser);
}
