package com.kyeonjuk.common.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

    @PersistenceContext   // 스프링 컨테이너가 EntityManager를 관리하도록 지정
    private EntityManager entityManager;  // JPA와 상호작용하기 위한 핵심 인터페이스 (DB와의 모든 작업 처리)

    @Bean                 // JPAQueryFactory : QueryDSL을 사용하여 쿼리 작성,실행에 필요
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
