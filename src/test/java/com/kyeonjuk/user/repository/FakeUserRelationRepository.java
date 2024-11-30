package com.kyeonjuk.user.repository;

import com.kyeonjuk.user.application.interfaces.UserRelationRepository;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.ui.dto.GetUserRelationListResponseDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FakeUserRelationRepository implements UserRelationRepository {
    private final Set<Relation> store = new HashSet<>();

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        return store.contains(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void save(User user, User targetUser) {
        store.add(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void delete(User user, User targetUser) {
        store.remove(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public List<GetUserRelationListResponseDto> findAllByFollowingUserId(Long followingUserId) {
        return List.of();
    }

    @Override
    public List<GetUserRelationListResponseDto> findAllByFollowerUserId(Long followerUserId) {
        return List.of();
    }
}

record Relation(Long userId, Long targetUserId) {}
