package com.kyeonjuk.user.ui;

import com.kyeonjuk.common.ui.Response;
import com.kyeonjuk.user.application.UserRelationService;
import com.kyeonjuk.user.application.dto.FollowUserRequestDto;
import com.kyeonjuk.user.ui.dto.GetUserRelationListResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto dto) {
        userRelationService.unFollow(dto);

        return Response.ok(null);
    }

    /*
       팔로워 리스트 조회
    */
    @GetMapping("/getFollowerList/{targetUserId}")
    public Response<List<GetUserRelationListResponseDto>> getFollowerList(@PathVariable Long targetUserId) {

        List<GetUserRelationListResponseDto> result = userRelationService.getFollowerList(targetUserId);

        return Response.ok(result);
    }

    /*
       팔로잉 리스트 조회
    */
    @GetMapping("/getFollowingList/{targetUserId}")
    public Response<List<GetUserRelationListResponseDto>> getFollowingList(@PathVariable Long targetUserId) {

        List<GetUserRelationListResponseDto> result = userRelationService.getFollowingList(targetUserId);

        return Response.ok(result);
    }

}
