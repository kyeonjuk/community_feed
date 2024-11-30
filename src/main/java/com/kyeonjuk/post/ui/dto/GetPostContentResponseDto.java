package com.kyeonjuk.post.ui.dto;

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
public class GetPostContentResponseDto extends GetContentResponseDto{

    private Integer commentCount;
    private boolean likedByMe; // 사용자가 이 게시글을 좋아요했는지 여부
    private String contentImageUrl; // 추가된 contentImageUrl 필드
}
