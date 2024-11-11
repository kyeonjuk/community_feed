package com.kyeonjuk.admin.ui.dto.posts;

import com.kyeonjuk.common.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostTableRequestDto extends Pageable {

    private Long postId;
}
