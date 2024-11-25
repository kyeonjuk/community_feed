package com.kyeonjuk.user.ui.dto;

import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.user.application.dto.GetUserResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetProfileResponseDto {

    private Long myUserId;
    private Long otherUserId;
    private List<Post> postList;
    private GetUserResponseDto profile;
    private boolean isFollowing;
}
