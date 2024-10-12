package com.kyeonjuk.user.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  // Q객체 불러오기

}
