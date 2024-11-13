<div id="top"></div>

<div align='center'>

<h1><b>커뮤니티 SNS형 피드 프로젝트</b></h1>
<h3><b>프로젝트 부제목</b></h3>

🔗 [배포 링크](https://)

<img src="" alt="intro title image"/>

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
10. [프로젝트 회고](#10)
11. [시작 가이드](#11)

<br />

## <span id="1">🚩 1. 프로젝트 소개</span>

<a href="https://www.notion.so/SNS-137a0f9a44d180a6b1e9f130d59171b7?pvs=4">![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)</a>

프로젝트에 대한 전반적인 소개를 여기에 적어주세요.

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="2">📅 2. 개발 일정</span>

> 프로젝트 개발 기간: 2024.00.00 ~ 2024.00.00

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


### Hosting

![Vercel](https://img.shields.io/badge/vercel-%23000000.svg?style=for-the-badge&logo=vercel&logoColor=white)

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

- `main`:
- `dev`:
-

<br>

> 디렉토리 구조

```
.
├── src
│   ├── components
│   ├── pages
│   ├── redux
│   ├── utils
│   └── App.js
├── public
│   ├── index.html
│   └── favicon.ico
└── package.json

```

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="7">7. 💻 주요 기능 소개</span>

프로젝트의 주요 기능을 GIF를 첨부하여 설명해주세요.

### 1) 홈

| - 화면                                            | - 화면                                            | - 화면                                            |
| ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- |
| <img src="" alt="-화면" width="288" height="608"> | <img src="" alt="-화면" width="288" height="608"> | <img src="" alt="-화면" width="288" height="608"> |

### 2) 게시글

| 상세페이지 화면                                   | 게시물 작성 - ??                                  | 게시물 작성 - ??                                  |
| ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- |
| <img src="" alt="-화면" width="288" height="608"> | <img src="" alt="-화면" width="288" height="608"> | <img src="" alt="-화면" width="288" height="608"> |

### 3) 404 & 로딩 화면

| - 화면                                            | - 화면                                            | - 화면                                            |
| ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- |
| <img src="" alt="-화면" width="288" height="608"> | <img src="" alt="-화면" width="288" height="608"> | <img src="" alt="-화면" width="288" height="608"> |

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="8">✨ 8. 주요 코드</span>

<details>
<summary> 주요 코드에 대한 설명을 입력하세요. </summary>

<div>
설명

```jsx

```

</div>
</details>

<br>

<details>
<summary> 주요 코드에 대한 설명을 입력하세요. </summary>

<div>
설명

```jsx

```

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

<코드 추가하기 !!!!!!><br />

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

## <span id="10">10. 📝 프로젝트 회고</span>

프로젝트 진행 후 느낀 점과 개선할 점을 적어주세요. 블로그에 작성하셨다면 블로그 링크를 첨부해주세요.

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>

<br>

## <span id="11">11. 🛠️ 시작 가이드</span>

### Installation
1. application.yml 코드 추가 (DB연결, JPA, secret-Key, fcm - Firebase 인증서 파일(json) 읽어오기)
2. /resources/community-service-adminsdk.json 파일 추가 (Firebase 인증서 파일(json))
3. /resources/templates/login.html 에 vapidKey 코드 변경, const firebaseConfig 안의 코드 변경   


```
$ git clone https://github.com/MyNameSieun/OH-YO.git
$ cd OH-YO
$ yarn
& yarn start
```

<br>

<!-- Top Button -->
<p style='background: black; width: 32px; height: 32px; border-radius: 50%; display: flex; justify-content: center; align-items: center; margin-left: auto;'><a href="#top" style='color: white; '>▲</a></p>
