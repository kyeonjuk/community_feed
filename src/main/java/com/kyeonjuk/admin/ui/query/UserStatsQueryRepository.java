package com.kyeonjuk.admin.ui.query;

import com.kyeonjuk.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import java.util.List;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);

}
