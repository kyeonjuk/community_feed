<div id="top"></div>

<div align='center'>

<h1><b>커뮤니티 SNS형 피드 프로젝트</b></h1>

![image](https://github.com/user-attachments/assets/508a79b0-b3ed-4e54-b8eb-9953ef032c14)

https://github.com/user-attachments/assets/5ab43078-9d0d-4b77-9317-d2c22e2b3fc3

</div>

<br>

## 0. 목차

1. [프로젝트 소개](#1)
2. [개발 일정](#2)
3. [기술 스택](#3)
4. [라이브러리 사용 이유](#4)
5. [컨벤션](#5)
6. [브랜치 및 디렉토리 구조](#6)
7. [주요 기능 소개](#7)
8. [주요 코드 ](#8)
9. [트러블 슈팅](#9)
10. [시작 가이드](#10)

<br />

## <span id="1">🚩 1. 프로젝트 소개</span>

<a href="https://www.notion.so/SNS-137a0f9a44d180a6b1e9f130d59171b7?pvs=4">![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)</a>


<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="2">📅 2. 개발 일정</span>

> 프로젝트 개발 기간: 2024.11.01 ~ 2024.12.01

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="3">📚 3. 기술 스택</span>

### Environment

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-%23FF5F00.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

### Config

![Spring Config](https://img.shields.io/badge/Spring_Config-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)


### Development

![Java](https://img.shields.io/badge/Java-%23F7B731.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-%236DB33F.svg?style=for-the-badge&logo=spring-security&logoColor=white)
![QueryDSL](https://img.shields.io/badge/QueryDSL-%2338A3D8.svg?style=for-the-badge&logo=querydsl&logoColor=white)
![JPQL](https://img.shields.io/badge/JPQL-%23338D91.svg?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-%234E77A8.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-%232496ED.svg?style=for-the-badge&logo=docker&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-%23000C1B.svg?style=for-the-badge&logo=gradle&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-%23A1C7B2.svg?style=for-the-badge&logo=h2&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23495B6B.svg?style=for-the-badge&logo=thymeleaf&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-%23A8B9C4.svg?style=for-the-badge&logo=junit&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-%23DC382D.svg?style=for-the-badge&logo=redis&logoColor=white)


### Communication

![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="4">❓ 4. 라이브러리 사용 이유</span>


> Redis
  - 인메모리 DB이자 캐시시스템인 Redis를 사용해 멱등키 처리같은 빠른 응답 시간이 필요한 API에 사용
  - 데이터베이스 부하를 줄이고, 데이터 접근 속도를 빠르게 하기 위해
<br>

> MySQL
  - 중요한 데이터의 일관성과 무결성 보장
  - 테이블 형식으로 저장하여 구조화된 데이터 저장

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="5">🤝 5. 컨벤션</span>

### prettier

```json
{
  "printWidth": 80,
  "tabWidth": 2,
  "singleQuote": true,
  "trailingComma": "all",
  "semi": false
}
```

### 커밋 컨벤션

```
feat: 새로운 기능 추가
fix: 버그 수정
docs: 문서 수정
style: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
refactor: 코드 리팩토링
test: 테스트 추가, 테스트 리팩토링 (프로덕션 코드 변경 없음)
chore: 빌드 업무 수정, 패키지 매니저 설정 수정 (프로덕션 코드 변경 없음)
```

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## 6.<span id="6"> 🗂️ 브랜치 및 디렉토리 구조</span>

> 브랜치

- `main`

<br>

> 디렉토리 구조
<details>

```

├─java
│  └─com
│      └─kyeonjuk
│          │  Application.java
│          │  
│          ├─admin
│          │  ├─repository
│          │  │      AdminTableQueryRepositoryImpl.java
│          │  │      UserStateQueryRepositoryImpl.java
│          │  │      
│          │  └─ui
│          │      │  AdminController.java
│          │      │  
│          │      ├─dto
│          │      │  │  GetTableListResponse.java
│          │      │  │  
│          │      │  ├─posts
│          │      │  │      GetPostTableRequestDto.java
│          │      │  │      GetPostTableResponseDto.java
│          │      │  │      
│          │      │  └─users
│          │      │          GetDailyRegisterUserResponseDto.java
│          │      │          GetUserTableRequestDto.java
│          │      │          GetUserTableResponseDto.java
│          │      │          
│          │      └─query
│          │              AdminTableQueryRepository.java
│          │              UserStatsQueryRepository.java
│          │              
│          ├─auth
│          │  ├─application
│          │  │  │  AuthService.java
│          │  │  │  EmailService.java
│          │  │  │  
│          │  │  ├─dto
│          │  │  │      CreateUserAuthRequestDto.java
│          │  │  │      LoginRequestDto.java
│          │  │  │      SendEmailRequestDto.java
│          │  │  │      UserAccessTokenResponseDto.java
│          │  │  │      
│          │  │  └─Interfaces
│          │  │          EmailSendRepository.java
│          │  │          EmailVerificationRepository.java
│          │  │          UserAuthRepository.java
│          │  │          
│          │  ├─domain
│          │  │      Email.java
│          │  │      Password.java
│          │  │      RandomTokenGenerator.java
│          │  │      SHA256.java
│          │  │      TokenProvider.java
│          │  │      UserAuth.java
│          │  │      UserRole.java
│          │  │      
│          │  ├─repository
│          │  │  │  EmailSendRepositoryImpl.java
│          │  │  │  EmailVerificationRepositoryImpl.java
│          │  │  │  UserAuthRepositoryImpl.java
│          │  │  │  
│          │  │  ├─entity
│          │  │  │      EmailVerificationEntity.java
│          │  │  │      UserAuthEntity.java
│          │  │  │      
│          │  │  └─jpa
│          │  │          JpaEmailVerificationRepository.java
│          │  │          JpaUserAuthRepository.java
│          │  │          
│          │  └─ui
│          │      │  AuthModelController.java
│          │      │  LoginController.java
│          │      │  SignUpController.java
│          │      │  
│          │      └─dto
│          │              VerifyEmailResponseDto.java
│          │              
│          ├─common
│          │  ├─application
│          │  │      NotificationService.java
│          │  │      
│          │  ├─config
│          │  │      AuthConfig.java
│          │  │      FcmConfig.java
│          │  │      QueryDslConfig.java
│          │  │      
│          │  ├─domain
│          │  │  │  Pageable.java
│          │  │  │  PositiveIntegerCounter.java
│          │  │  │  
│          │  │  └─exception
│          │  │          ErrorCode.java
│          │  │          
│          │  ├─idempotency
│          │  │  │  Idempotency.java
│          │  │  │  IdempotencyAspect.java
│          │  │  │  IdempotencyRepository.java
│          │  │  │  Idempotent.java
│          │  │  │  
│          │  │  └─repostiory
│          │  │      │  IdempotencyRepositoryImpl.java
│          │  │      │  
│          │  │      ├─entity
│          │  │      │      IdempotencyEntity.java
│          │  │      │      
│          │  │      └─jpa
│          │  │              JpaIdempotencyRepository.java
│          │  │              
│          │  ├─principal
│          │  │      AuthPrincipal.java
│          │  │      AuthPrincipalArgumentResolver.java
│          │  │      UserPrincipal.java
│          │  │      
│          │  ├─repository
│          │  │  │  NotificationRepository.java
│          │  │  │  
│          │  │  └─entity
│          │  │          NotificationEntity.java
│          │  │          TimeBaseEntity.java
│          │  │          
│          │  ├─ui
│          │  │  │  BaseException.java
│          │  │  │  GlobalExceptionHandler.java
│          │  │  │  HealthCheckController.java
│          │  │  │  Response.java
│          │  │  │  
│          │  │  └─dto
│          │  │          SaveNotificationRequestDto.java
│          │  │          
│          │  └─utils
│          │          ResponseObjectMapper.java
│          │          TimeCalculator.java
│          │          
│          ├─message
│          │  ├─application
│          │  │  └─interfaces
│          │  │          MessageRepository.java
│          │  │          
│          │  ├─component
│          │  │      MailComponents.java
│          │  │      
│          │  └─repository
│          │      │  FcmMessageRepositoryImpl.java
│          │      │  JpaFcmTokenRepository.java
│          │      │  
│          │      └─entity
│          │              FcmTokenEntity.java
│          │              
│          ├─post
│          │  ├─application
│          │  │  │  CommentService.java
│          │  │  │  PostService.java
│          │  │  │  
│          │  │  ├─dto
│          │  │  │      CreateCommentRequestDto.java
│          │  │  │      CreatePostRequestDto.java
│          │  │  │      GetPostResponseDto.java
│          │  │  │      LikeCommentRequestDto.java
│          │  │  │      LikePostRequestDto.java
│          │  │  │      UpdateCommentRequestDto.java
│          │  │  │      UpdatePostRequestDto.java
│          │  │  │      
│          │  │  └─Interfaces
│          │  │          CommentRepository.java
│          │  │          LikeCommentRepository.java
│          │  │          LikePostRepository.java
│          │  │          PostRepository.java
│          │  │          
│          │  ├─domain
│          │  │  │  Post.java
│          │  │  │  
│          │  │  ├─comment
│          │  │  │      Comment.java
│          │  │  │      
│          │  │  ├─common
│          │  │  │      DatetimeInfo.java
│          │  │  │      
│          │  │  └─content
│          │  │          CommentContent.java
│          │  │          Content.java
│          │  │          PostContent.java
│          │  │          PostPublicationState.java
│          │  │          
│          │  ├─repository
│          │  │  │  CommentRepositoryImpl.java
│          │  │  │  LikeRepositoryImpl.java
│          │  │  │  PostRepositoryImpl.java
│          │  │  │  
│          │  │  ├─entity
│          │  │  │  ├─comment
│          │  │  │  │      CommentEntity.java
│          │  │  │  │      
│          │  │  │  ├─like
│          │  │  │  │      LikeEntity.java
│          │  │  │  │      LikeIdEntity.java
│          │  │  │  │      LikeTarget.java
│          │  │  │  │      
│          │  │  │  └─post
│          │  │  │          PostEntity.java
│          │  │  │          PostPublicationStateConvert.java
│          │  │  │          UserPostQueueEntity.java
│          │  │  │          
│          │  │  ├─jpa
│          │  │  │      JpaCommentRepository.java
│          │  │  │      JpaLikeRepository.java
│          │  │  │      JpaPostRepository.java
│          │  │  │      JpaUserPostQueueRepository.java
│          │  │  │      
│          │  │  └─post_queue
│          │  │          UserPostQueueCommandRepository.java
│          │  │          UserPostQueueCommandRepositoryImpl.java
│          │  │          UserPostQueueQueryRepository.java
│          │  │          UserPostQueueQueryRepositoryImpl.java
│          │  │          UserQueueRedisRepository.java
│          │  │          UserQueueRedisRepositoryImpl.java
│          │  │          
│          │  └─ui
│          │      │  CommentController.java
│          │      │  FeedController.java
│          │      │  FeedModelController.java
│          │      │  PostController.java
│          │      │  
│          │      └─dto
│          │              GetContentResponseDto.java
│          │              GetPostContentResponseDto.java
│          │              GetPostMainResponseDto.java
│          │              
│          ├─sampleData
│          │      SampleDataLoader.java
│          │      
│          └─user
│              ├─application
│              │  │  UserRelationService.java
│              │  │  UserService.java
│              │  │  
│              │  ├─dto
│              │  │      CreateUserRequestDto.java
│              │  │      FollowUserRequestDto.java
│              │  │      GetUserListResponseDto.java
│              │  │      GetUserResponseDto.java
│              │  │      
│              │  └─interfaces
│              │          UserRelationRepository.java
│              │          UserRepository.java
│              │          
│              ├─domain
│              │      User.java
│              │      UserInfo.java
│              │      
│              ├─repository
│              │  │  UserRelationRepositoryImpl.java
│              │  │  UserRepositoryImpl.java
│              │  │  
│              │  ├─entity
│              │  │      UserEntity.java
│              │  │      UserRelationEntity.java
│              │  │      UserRelationIdEntity.java
│              │  │      
│              │  └─jpa
│              │      │  JpaUserListPagingQueryRepository.java
│              │      │  JpaUserListQueryRepository.java
│              │      │  JpaUserRepository.java
│              │      │  
│              │      └─user
│              │          └─userRelation
│              │                  JpaUserRelationRepository.java
│              │                  
│              └─ui
│                  │  UserController.java
│                  │  UserModelController.java
│                  │  UserNotificationController.java
│                  │  UserRelationController.java
│                  │  UserSearchController.java
│                  │  
│                  └─dto
│                          GetProfileRequestDto.java
│                          GetProfileResponseDto.java
│                          GetSearchByUserNameResponseDto.java
│                          GetUnreadNotificationResponseDto.java
│                          GetUserRelationListResponseDto.java
│                          PatchPasswordRequestDto.java
│                          PatchProfileImageRequestDto.java
│                          PatchProfileRequestDto.java
│                          
└─resources
    │  application.yml
    │  community-service-adminsdk.json
    

```

<br>

</details>
<br>
<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="7">7. 💻 주요 기능 소개</span>

https://github.com/user-attachments/assets/5ab43078-9d0d-4b77-9317-d2c22e2b3fc3


### 1) 홈

| 로그인 화면                                          
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/15a00db6-8b18-4836-8fc3-e1826e290695)
 

| 회원가입 화면                                          
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/973b6e48-daa9-4a3c-8844-011dc541fffd)



### 2) 게시글

| 피드 화면                                 
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/49fb0dd0-dcc9-4af5-a836-6d5bb3fe7fc3)



| 게시글 화면                                      
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/e0950377-78b8-4a54-a9c5-a553884057d3)



| 게시글 등록 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/8454cb21-c3ef-4369-8b8c-40f15d17a2aa)



| 게시글 수정 화면                                      
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/e8a9918d-3161-4343-a2bc-363613552681)



### 3) 마이페이지

| 프로필 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/38777413-858c-452d-a256-c55ded9828db)



| 팔로잉,팔로우 조회 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/7279f975-4e51-413c-bbf0-bdc016e4eb21)



| 상대방 프로필 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/eba74235-1625-4394-93ca-db08ff18a180)
 


| 프로필 등록 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/b30fa430-4ced-48d6-888f-cb837533ecd8)
 


| 개인정보 수정 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/63714cf0-5259-49a8-be5b-c0ad9d36c23f)
 


### 4) etc

| 검색 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/bc6a2902-fe0d-49b7-8f93-e464082db8b0)



| 알림 조회 화면                                    
| ------------------------------------------------- |
| ![image](https://github.com/user-attachments/assets/1ef08794-e239-4f81-8bdd-9ec4dc382d04)
<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="8">✨ 8. 주요 코드</span>

<details>
<summary> API 동시성, 중복 처리 문제 (멱등성) </summary>

<div>
  <br />
  중복 처리 문제를 해결하기 위해, 멱등성(Idempotency)을 적용하여 문제를 해결<br />
- Idempotency 커스텀 어노테이션 추가<br />
  - API에 어노테이션으로 멱등성 적용<br /> 
- Idempotency Key 도입<br />
  - header에 Idempotency-Key 필드에 랜덤한 UUID키값을 전달<br />
- Idempotency 저장소 구현<br />
  - 처음 실행시 Idempotency-Key를 DB에 저장<br />
  - 중복 실행시 저장된 Idempotency-Key 확인 후 중복방지 처리한 Response 반환<br />
<br />
  
![image](https://github.com/user-attachments/assets/cf829ac2-14ab-4cc2-a876-412c6cf7d27f)

<br />
</div>
</details>

<br>

<details>
<summary> 커스텀 어노테이션 (header의 access 토큰 조회) 생성 </summary>

<div>
  <br />
  header 의 Authorization 필드의 "Bearer " 타입의 토큰을 파싱해서 정보 조회<br />
  <br />
  
![image](https://github.com/user-attachments/assets/4ff2e4b3-a0a5-48c8-b8b5-ed0a058d7972)

<br />
</div>
</details>

<br>
<details>
<summary> QueryDSL 주요 코드 </summary>

<div>
  
```jsx
@Repository
@Profile({"!test"})
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final JPAQueryFactory queryFactory;

    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;
    private static final QCommentEntity commentEntity = QCommentEntity.commentEntity;

    public List<GetContentResponseDto> getCommentResponse(Long postId, Long userId, Long lastCommentId) {
        return queryFactory
            .select(
                Projections.fields(    
                    GetContentResponseDto.class,    
                    commentEntity.id.as("id"),         
                    commentEntity.content.as("content"),
                    commentEntity.author.id.as("authorId"),
                    commentEntity.author.id.as("userId"),
                    commentEntity.author.name.as("userName"),
                    commentEntity.author.profileImageUrl.as("userProfileImageUrl"),
                    commentEntity.regDt.as("createdAt"),
                    commentEntity.updDt.as("updatedAt"),
                    commentEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe")   
                )
            )
            .from(commentEntity)
            .leftJoin(likeEntity).on(hasLikeComment(userId))
            .where(
                commentEntity.post.id.eq(postId),
                hasCommentLastData(lastCommentId)  
            )
            .orderBy(commentEntity.id.desc())
            .limit(20)
            .fetch();
    }

    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory
            .select(
                Projections.fields(     
                    GetPostContentResponseDto.class,    
                    postEntity.id.as("id"),        
                    postEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    userEntity.profileImageUrl.as("userProfileImageUrl"),
                    postEntity.regDt.as("createdAt"),
                    postEntity.updDt.as("updatedAt"),
                    postEntity.commentCount.as("commentCount"),
                    postEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe")    
                )
            )
            .from(userPostQueueEntity)
            .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))
            .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))
            .leftJoin(likeEntity).on(hasLike(userId))
            .where(
                userPostQueueEntity.userId.eq(userId),
                hasLastData(lastContentId)  
            )
            .orderBy(userPostQueueEntity.postId.desc())
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return postEntity.id.lt(lastId);   
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return postEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("POST"))
            .and(likeEntity.id.userId.eq(userId));
    }

    private BooleanExpression hasCommentLastData(Long lastId) {
        if (lastId == null || lastId <= 0) {
            return null;  
        }

        return commentEntity.id.lt(lastId); 
    }

    private BooleanExpression hasLikeComment(Long userId) {
        if (userId == null) {
            return null;
        }

        return commentEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("COMMENT"))
            .and(likeEntity.id.userId.eq(userId));
    }

}
```
<br />
</div>
</details>

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="9">🚦 9. 트러블 슈팅</span>

<details>
<summary> API 동시성, 중복 처리 문제 (멱등성) </summary>

<div>
<br>
  
1. 문제 상황 <br />
  게시글이나 댓글에 대한 좋아요 중복, 네트워크 지연이나 서버오류의 이유로 사용자가 다시 버튼을 누른경우<br />
  ㄴ 예시 1) 사용자가 버튼을 여러 번 눌러서 "좋아요"를 여러 번 클릭하게 되면, 동일한 좋아요 요청이 여러 번 처리되어 원하지 않는 결과가 발생<br />

![image](https://github.com/user-attachments/assets/cf829ac2-14ab-4cc2-a876-412c6cf7d27f)

2. 해결방안 <br />
  중복 처리 문제를 해결하기 위해, 멱등성(Idempotency)을 적용하여 문제를 해결
- Idempotency 커스텀 어노테이션 추가
  - API에 어노테이션으로 멱등성 적용 
- Idempotency Key 도입
  - header에 Idempotency-Key 필드에 랜덤한 UUID키값을 전달
- Idempotency 저장소 구현
  - 처음 실행시 Idempotency-Key를 DB에 저장
  - 중복 실행시 저장된 Idempotency-Key 확인 후 중복방지 처리한 Response 반환

3. 성능 고려 <br />
  Idempotency를 도입하는 과정에서 성능 저하가 있을 수 있으므로,<br />
  Redis를 사용하여 Idempotency Key와 응답을 저장해 빠른 속도로 중복 요청을 처리<br />

</div>
</details>

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>


## <span id="10">10. 🛠️ 시작 가이드</span>

### Installation
1. application.yml 코드 추가 (DB연결, JPA, secret-Key, fcm - Firebase 인증서 파일(json) 읽어오기, Gmail SMTP 서버로 이메일 전송 설정)
2. /resources/community-service-adminsdk.json 파일 추가 (Firebase 인증서 파일(json))
3. /resources/templates/login.html 에 vapidKey 코드 변경, const firebaseConfig 안의 코드 변경   

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>
