package com.kyeonjuk.user.ui.dto;

import com.kyeonjuk.user.domain.UserInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRelationListResponseDto {

    private String name;
    private String profileImageUrl;
    private Long relationUserId;
}
