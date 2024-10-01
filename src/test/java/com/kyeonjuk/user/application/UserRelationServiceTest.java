package com.kyeonjuk.user.application;

import static org.junit.jupiter.api.Assertions.*;

import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.application.dto.FollowUserRequestDto;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.repository.FakeUserRelationRepository;
import com.kyeonjuk.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {

    private final FakeUserRepository fakeUserRepository = new FakeUserRepository();
    private final UserService userService = new UserService(fakeUserRepository);
    private final FakeUserRelationRepository fakeUserRelationRepository
        = new FakeUserRelationRepository();
    private final UserRelationService userRelationService
        = new UserRelationService(userService, fakeUserRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("홍길동", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        // when
        userRelationService.follow(requestDto);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        // given
        userRelationService.follow(requestDto);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        // given
        this.requestDto = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateTwoUserFollowed_whenUnFollow_thenCountIsZero() {
        //given
        userRelationService.follow(requestDto);

        // when
        userRelationService.unFollow(requestDto);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUserUnFollowed_whenUnFollow_thenUserThrowError() {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnFollow_thenUserThrowError() {
        // given
        this.requestDto = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(requestDto));
    }

}