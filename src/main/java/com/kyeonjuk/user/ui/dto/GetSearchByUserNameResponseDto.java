package com.kyeonjuk.user.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetSearchByUserNameResponseDto {

    private Long id;
    private String name;
    private String profileImageUrl;

}
