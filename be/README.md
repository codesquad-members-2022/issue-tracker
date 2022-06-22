## 1주차 수요일 리뷰 요청

### 진행 내용
#### AWS 인프라 구성
+ AWS 신규 VPC 생성 및 ec2 서브넷과 db 서브넷 구성
+ RDS(MySQL) 생성 및 환경 설정(Timezone, character set 등)

#### 프로젝트 아키텍처(안)
> ![image](https://user-images.githubusercontent.com/82401504/173318027-e3b76cd1-f953-4bfe-a0ea-491254fc4f5a.png)

#### 애플리케이션
+ 도메인 모델링 및 테이블 설계
+ 이슈 목록 조회 API 설계(진행 중)
  + 동적 검색을 위해 Querydsl 학습 중
+ Github OAuth 및 JWT 활용 인증 방식 설계(진행 중)

#### 도메인 및 테이블 설계(안)
+ 개념적 설계
> ![image](https://user-images.githubusercontent.com/82401504/173673131-ad29f4e1-af37-40f1-99ca-59e528e1b424.png)
+ ERD
> ![image](https://user-images.githubusercontent.com/82401504/173673708-8eee4da8-e9e8-4d61-bb83-255ec5a2b6e5.png)

## 1주차 금요일 리뷰 요청

### 진행 내용
#### 애플리케이션
+ 도메인 모델링 및 테이블 설계 보완(리뷰사항 반영)
+ 회원, 레이블, 마일스톤 목록 조회 API 구현
  + 이슈 목록 상단 탭(작성자, 담당자, 레이블, 마일스톤) 클릭 시 나오는 목록으로서 프론트 엔드에서 별도 요청
+ 이슈 목록 조회 API 설계 및 구현
  + 동적 검색을 위해 Querydsl 학습(인프런 김영한님 강의 참고)
+ Github OAuth 및 JWT 활용 인증 방식 설계(진행 중)

#### API 명세서
+ <a href="https://near-snipe-0de.notion.site/API-Description-be8af8c4d6b44f668f3a8f03368ea978">API 명세서 확인하기(클릭)</a><br/>

#### 도메인 및 테이블 설계
+ ERD
> ![image](https://user-images.githubusercontent.com/82401504/174156926-30c45c19-01ab-441b-994b-7a7714fa058e.PNG)

## 2주차 수요일 리뷰 요청

### 진행 내용
#### 애플리케이션
##### 완료
+ 이슈 목록 조회 API 기능 개선 및 보완
  + status 파라미터의 기본 값을 'OPEN'으로 설정
  + 응답 데이터 상에 assignee 관련 데이터 추가
  + 응답 데이터 상에 이슈 상태별 개수 데이터 추가
  + 제외 조건 및 여러 개의 라벨로 이슈 목록 검생 가능
+ Github OAuth 연동 로그인 기능 구현
+ JWT 활용 로그인 검증 기능 구현
  + access 및 refresh token 발행, refresh token redis에 저장
  + access 토큰 재발급 및 로그아웃 기능 구현
+ 이슈 상태(OPEN, CLOSE) 변경 API 기능 구현
+ 로그인 상태 정보 조회 API 기능 구현

##### Github OAuth 연동 로그인 흐름도
> ![image](https://user-images.githubusercontent.com/82401504/174836244-fc48ced7-2d43-40d5-b2a7-7d3998b4ffff.png)

##### JWT 로그인 검증 흐름도
> ![image](https://user-images.githubusercontent.com/82401504/174836367-4dd4d730-5b21-45b6-a6ff-f988e9eae83a.png)

##### 진행 예정
+ S3 이미지 업로드 기능 구현

#### API 명세서
+ <a href="https://near-snipe-0de.notion.site/API-Description-be8af8c4d6b44f668f3a8f03368ea978">API 명세서 확인하기(클릭)</a><br/>

#### 인프라
+ Nginx, redis 도커 컨테이너 생성(완료)
+ 깃허브 액션 및 도커 활용 스프링 부트 웹 앱 배포(완료)
+ 깃허브 액션 및 도커 활용 리액트 웹 앱 배포(진행 예정)

#### 도메인 및 테이블 설계
+ ERD
> ![image](https://user-images.githubusercontent.com/82401504/174830795-b4674c2b-1faa-4f00-b189-64d9805d64d8.png)
