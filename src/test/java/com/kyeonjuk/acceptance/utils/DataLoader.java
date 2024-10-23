package com.kyeonjuk.acceptance.utils;

import static com.kyeonjuk.acceptance.steps.UserAcceptanceSteps.createUser;
import static com.kyeonjuk.acceptance.steps.UserAcceptanceSteps.followUser;

import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

// 샘플데이터 생성
@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        // 테스트 API 호출
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
