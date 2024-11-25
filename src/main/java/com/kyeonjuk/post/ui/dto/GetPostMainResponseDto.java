package com.kyeonjuk.post.ui.dto;

import com.kyeonjuk.post.domain.Post;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostMainResponseDto {

    private Post post;
    private List<GetContentResponseDto> comment;

}
