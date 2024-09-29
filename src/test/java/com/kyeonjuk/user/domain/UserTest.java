package com.kyeonjuk.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo = new UserInfo("홍길동", "naver");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        // when
        boolean value = user1.equals(user2);

        // then
        assertFalse(value);
    }

    @Test
    void givenSameIdUser_whenEqual_thenReturnTrue() {
        // given
        User user3 = new User(1L, userInfo);

        // when
        boolean value = user1.equals(user3);

        // then
        assertTrue(value);
    }

    @Test
    void givenTwoUser_whenHashCode_thenNotEqual() {
        // when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        // then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTwoUser_whenHashCode_thenEqual() {
        // given
        User user3 = new User(1L, userInfo);

        // when
        int hashCode1 = user1.hashCode();
        int hashCode3 = user3.hashCode();

        // then
        assertEquals(hashCode1, hashCode3);
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        // when
        user1.follow(user2);

        // then
        assertEquals(0, user1.getFollowerCount());
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());

    }

    @Test
    void givenTwoUserUser1FollowUser2_whenUser1UnfollowUser2_thenDecreaseUserCount() {
        // given
        user1.follow(user2);

        // when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
    }

    @Test
    void givenTwoUser_whenUser1UnfollowUser2_thenCountIsZero() {
        // when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
    }
}

