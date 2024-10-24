package com.kyeonjuk.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")    // 테스트 환경에서만 활성화
@Component          // 스프링의 컴포넌트 스캔을 통해 자동으로 Bean 등록
@Slf4j              // 로깅
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;

    // Bean Factory에 의해 모든 Bean들이 설정되고(Entity 설정 후) 난 후에 실행이 되는 메소드
    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = entityManager.getMetamodel().getEntities().stream()
            .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
            .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
            .toList();

        // 초기화 안할
        notGeneratedIdTableNames = List.of("community_user_relation", "community_like");
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        // 연결된 외래키 끊기
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (String tableName : tableNames) {
            // 테이블 삭제
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();

            if (!notGeneratedIdTableNames.contains(tableName)) {
                // 자동 증가 ID값 초기화
                entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }

        // 외래키 다시 연결
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
