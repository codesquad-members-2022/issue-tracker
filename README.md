# issue-tracker

## Members
- [Piggy](https://github.com/Piggy-Seob) (iOS)
- [Beck](https://github.com/SangHwi-Back) (iOS)

## App Overview
 
Github Issue 관련 REST-API를 이용한 깃허브 이슈 트래커
_코드스쿼드 6월 2-4주차 프로젝트 과제_

## Simulation

그림(GIF) 들어갈 예정

## Application Spec

- UIKit
- Alamofire
- Snapkit
- MVVM

## Weekly Goal

|Week| 주간 목표 | 주요 성과 |
|---|:--|:--|
|1|앱 구조 구체화 및 페어 프로그래밍|앱 구조 잡은 후 테스트로 직접 구현해 봄|
|2|<li>OAuth, Issue CRUD/Query 구현</li><li>프로젝트 민감 정보 관리</li>||
|3|||

## Architecture Diagram

<figure>
    <div style="flow:left">
        <img alt="Architecture_All" src="https://user-images.githubusercontent.com/65931336/173571707-1b07c6fd-708f-4ac0-8028-a2eb370fff5e.png" width=64%>
        <img alt="Response Manner" src="https://user-images.githubusercontent.com/65931336/173573770-f0764e01-f5bf-4eb3-9bc7-9cc99ebde165.png" width=35%>
    </div>
    <figcaption>앱 전체 흐름 및 응답 방식</figcaption>
</figure>

[위키 URL](https://github.com/SangHwi-Back/issue-tracker/wiki/Architecture-%ED%98%91%EC%9D%98)

## Team Workflow

- 첫 화면은 페어 프로그래밍. 클래스 선언만 하고 내부 로직은 개인 역량으로.
   - 각자 작업한 코드를 이해하지 못하는 상황을 최대한 줄이기 위함.
- feature branch를 merge 하고 싶을 땐 PR을 통해서 팀원이 확인 후 merge.
- 평일 10:00 ~ 데일리 스크럼 & 진행 공유 및 이슈 생성.
- 리뷰어 피드백 반영은 공유 후, hotfix branch 만들어서 진행.
