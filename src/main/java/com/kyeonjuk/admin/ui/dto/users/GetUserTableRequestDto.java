package com.kyeonjuk.admin.ui.dto.users;

import com.kyeonjuk.common.domain.Pageable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableRequestDto extends Pageable {

    private String name;
}
