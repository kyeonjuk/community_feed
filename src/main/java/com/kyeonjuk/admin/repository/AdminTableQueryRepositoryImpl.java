package com.kyeonjuk.admin.repository;

import com.kyeonjuk.admin.ui.dto.GetTableListResponse;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableRequestDto;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableResponseDto;
import com.kyeonjuk.admin.ui.query.AdminTableQueryRepository;
import com.kyeonjuk.auth.repository.entity.QUserAuthEntity;
import com.kyeonjuk.user.repository.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QUserEntity userEntity = QUserEntity.userEntity;
    private final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;


    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTableData(
        GetUserTableRequestDto dto) {

        int total = jpaQueryFactory
            .select(userEntity.id)
            .from(userEntity)
            .where(likeName(dto.getName()))
            .fetch()
            .size();

        List<GetUserTableResponseDto> result = jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserTableResponseDto.class,
                    userEntity.id.as("id"),
                    userAuthEntity.email.as("email"),
                    userEntity.name.as("name"),
                    userAuthEntity.userRole.as("role"),
                    userEntity.regDt.as("createdAt"),
                    userEntity.updDt.as("updatedAt"),
                    userAuthEntity.lastLoginDt.as("lastLoginAt")
                )
            )
            .from(userEntity)
            .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
            .where(likeName(dto.getName()))
            .orderBy(userEntity.id.desc())
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .fetch();

        return new GetTableListResponse<>(total, result);
    }

    public BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        return userEntity.name.like("%" + name + "%");
    }
}
