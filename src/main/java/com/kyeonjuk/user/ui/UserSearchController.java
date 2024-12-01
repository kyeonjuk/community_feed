package com.kyeonjuk.user.ui;

import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.user.application.UserService;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.ui.dto.GetSearchByUserNameResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/search")
@RequiredArgsConstructor
public class UserSearchController {

    private final UserService userService;

    @GetMapping("/{userName}")
    public Response<List<GetSearchByUserNameResponseDto>> searchByUserName(@PathVariable String userName) {

        List<User> userList = userService.findByLikeUserName(userName);

        List<GetSearchByUserNameResponseDto> result = userList.stream()
            .map(user -> new GetSearchByUserNameResponseDto(user.getId(), user.getName(), user.getProfileImageUrl())).toList();

        return Response.ok(result);
    }
}
