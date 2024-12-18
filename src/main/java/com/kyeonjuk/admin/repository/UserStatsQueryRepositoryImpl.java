package com.kyeonjuk.admin.repository;

import static com.kyeonjuk.common.utils.TimeCalculator.getDateDaysAgo;

import com.kyeonjuk.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import com.kyeonjuk.admin.ui.query.UserStatsQueryRepository;
import com.kyeonjuk.user.repository.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {

        return queryFactory
            .select(
                Projections.fields(
                    GetDailyRegisterUserResponseDto.class,
                    userEntity.regDate.as("date"),
                    userEntity.count().as("count")  // count(*) 기능
                )
            )
            .from(userEntity)
            .where(userEntity.regDate.after(getDateDaysAgo(beforeDays)))
            .groupBy(userEntity.regDate)
            .orderBy(userEntity.regDate.asc())
            .fetch();
    }
}
