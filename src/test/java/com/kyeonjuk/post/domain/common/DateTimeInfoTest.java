package com.kyeonjuk.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import com.kyeonjuk.post.domain.Post;
import com.kyeonjuk.post.domain.content.PostContent;
import com.kyeonjuk.post.domain.content.PostPublicationState;
import com.kyeonjuk.user.domain.User;
import com.kyeonjuk.user.domain.UserInfo;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArgsUpdated() {
        // given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime localDateTime = dateTimeInfo.getDateTime();
        System.out.println("Local DateTime: " + localDateTime);

        // when
        dateTimeInfo.updateEditDateTime();
        System.out.println("Updated DateTime: " + dateTimeInfo.getDateTime());

        // then
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(localDateTime, dateTimeInfo.getDateTime());
    }
}