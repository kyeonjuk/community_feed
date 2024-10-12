plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.kyeonjuk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // mysql
    implementation("com.mysql:mysql-connector-j")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")   // QueryDSL을 JPA와 같이 사용
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")  // QueryDSL이 컴파일 타임에 타입 안전한 쿼리 메타 모델을 생성
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")    // JPA와 함께 사용할 수 있는 Jakarta 어노테이션 제공
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")  // JPA를 사용해 DB와 상호작용 API 제공

    // lombok
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // test
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

/**
 * QueryDSL Build Options
 */
// 자동 생성되는 Q객체의 저장위치 지정
val querydslDir = "${layout.projectDirectory}/build/generated/querydsl"

sourceSets {
    getByName("main").java.srcDirs(querydslDir)
}

tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory = file(querydslDir)
}

// clean을 할 경우 QueryDSL 파일도 같이 삭제
tasks.named("clean") {
    doLast {
        file(querydslDir).deleteRecursively()
    }
}