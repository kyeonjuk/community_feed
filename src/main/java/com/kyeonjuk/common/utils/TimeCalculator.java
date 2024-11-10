package com.kyeonjuk.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    // DateTime 형식 설정
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 임시 싱글톤 설정 (private 로 외부에서 생성자 사용 막기)
    private TimeCalculator() {

    }

    // 설정한 날짜의 값을 출력 <3일전이면 (현재날짜 - 3일)을 한 값>
    public static LocalDate getDateDaysAgo(int daysAgo) {
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(daysAgo);
    }

    // DateTime 형식에 맞게 getter
    public static String getFormattedDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        return dateTime.format(FORMATTER);
    }
}
