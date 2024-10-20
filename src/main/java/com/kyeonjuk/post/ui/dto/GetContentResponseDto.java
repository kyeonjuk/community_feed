package com.kyeonjuk.post.ui.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetContentResponseDto {

    private Long id;
    private String content;
    private Long userId;
    private String userName;
    private String userProfileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private boolean isLikedByMe;
}
