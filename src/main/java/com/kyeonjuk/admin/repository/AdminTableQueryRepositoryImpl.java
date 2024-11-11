package com.kyeonjuk.admin.repository;

import com.kyeonjuk.admin.ui.dto.GetTableListResponse;
import com.kyeonjuk.admin.ui.dto.posts.GetPostTableRequestDto;
import com.kyeonjuk.admin.ui.dto.posts.GetPostTableResponseDto;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableRequestDto;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableResponseDto;
import com.kyeonjuk.admin.ui.query.AdminTableQueryRepository;
import com.kyeonjuk.auth.repository.entity.QUserAuthEntity;
import com.kyeonjuk.post.repository.entity.post.QPostEntity;
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
    private final QPostEntity postEntity = QPostEntity.postEntity;


    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTableData(
        GetUserTableRequestDto dto) {

        int total = jpaQueryFactory
            .select(userEntity.id)
            .from(userEntity)
            .where(likeName(dto.getName()))
            .fetch()
            .size();

        // # 1. 유사 커버링 인덱스 역할 : id값으로 정렬, 조회 -> 데이터 조회시간 단축!!
        //  ㄴ name 컬럼때문에 완전한 커버링 인덱스는 아님!
        List<Long> ids = jpaQueryFactory
            .select(userEntity.id)
            .from(userEntity)
            .where(likeName(dto.getName()))
            .orderBy(userEntity.id.desc())
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .fetch();

        // # 2. where절을 인덱스가 설정된 id값으로 지정 -> 데이터 조회시간 단축!
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
            .where(userEntity.id.in(ids))   // in 문법 (리스트의 값 중 해당하는 값)
            .orderBy(userEntity.id.desc())
            .fetch();

        return new GetTableListResponse<>(total, result);
    }

    @Override
    public GetTableListResponse<GetPostTableResponseDto> getPostTableData(
        GetPostTableRequestDto dto) {

        int total = jpaQueryFactory
            .select(postEntity.id)
            .from(postEntity)
            .where(eqpostId(dto.getPostId()))
            .fetch()
            .size();

        List<Long> ids = jpaQueryFactory
            .select(postEntity.id)
            .from(postEntity)
            .where(eqpostId(dto.getPostId()))
            .orderBy(postEntity.id.desc())
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .fetch();

        List<GetPostTableResponseDto> result = jpaQueryFactory
            .select(
                Projections.fields(
                    GetPostTableResponseDto.class,
                    postEntity.id.as("postId"),
                    postEntity.author.id.as("userId"),
                    postEntity.author.name.as("userName"),
                    postEntity.content.as("content"),
                    postEntity.regDt.as("createdAt"),
                    postEntity.updDt.as("updatedAt")
                )
            )
            .from(postEntity)
            .where(postEntity.id.in(ids))   // in 문법 (리스트의 값 중 해당하는 값)
            .orderBy(postEntity.id.desc())
            .fetch();

        return new GetTableListResponse<>(total, result);
    }

    public BooleanExpression likeName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        return userEntity.name.like("%" + name + "%");
    }

    public BooleanExpression eqpostId(Long postId) {
        if (postId == null || postId.equals(0)) {
            return null;
        }

        return postEntity.id.eq(postId);
    }
}
