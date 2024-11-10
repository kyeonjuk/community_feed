package com.kyeonjuk.admin.ui.dto.users;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDailyRegisterUserResponseDto {

    private LocalDate date;
    private Long count;
}
