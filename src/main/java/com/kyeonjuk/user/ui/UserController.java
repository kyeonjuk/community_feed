package com.kyeonjuk.user.ui;

import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.application.dto.CreateUserRequestDto;
import com.kyeonjuk.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);

        return Response.ok(user.getId());
    }
}
