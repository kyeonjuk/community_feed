package com.kyeonjuk.user.ui;

import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.user.application.UserRelationService;
import com.kyeonjuk.user.application.dto.FollowUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.follow(dto);

        return Response.ok(null);
    }
}
