package com.kyeonjuk.admin.ui.dto.users;

import com.kyeonjuk.common.utils.TimeCalculator;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTableResponseDto {

    @Getter
    private Long id;

    @Getter
    private String email;

    @Getter
    private String name;

    @Getter
    private String role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }

    public String getLastLoginAt() {
        return TimeCalculator.getFormattedDate(lastLoginAt);
    }
}
