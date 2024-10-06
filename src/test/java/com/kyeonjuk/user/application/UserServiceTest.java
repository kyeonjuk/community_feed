package com.kyeonjuk.user.application;

import static org.junit.jupiter.api.Assertions.*;

import com.kyeonjuk.fake.FakeObjectFactory;
import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    /*
        User 저장 확인
     */
    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("홍길동", "naver");

        // when
        User user1 = userService.createUser(dto);

        // then
        User user2 = userService.getUser(user1.getId());
        UserInfo userInfo = user2.getInfo();

        assertEquals(user1.getId(), user2.getId());
        assertEquals(dto.name(), userInfo.getName());
    }
}
