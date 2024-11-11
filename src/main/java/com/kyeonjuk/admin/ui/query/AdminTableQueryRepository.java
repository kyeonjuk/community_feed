package com.kyeonjuk.admin.ui.query;

import com.kyeonjuk.admin.ui.dto.GetTableListResponse;
import com.kyeonjuk.admin.ui.dto.posts.GetPostTableRequestDto;
import com.kyeonjuk.admin.ui.dto.posts.GetPostTableResponseDto;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableRequestDto;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {

    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
    GetTableListResponse<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto dto);
}
