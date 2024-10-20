package com.kyeonjuk.user.application;

import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.application.dto.GetUserResponseDto;
import com.kyeonjuk.user.application.interfaces.UserRepository;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        return new GetUserResponseDto(getUser(id));
    }
}
