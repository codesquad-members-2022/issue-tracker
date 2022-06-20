# Issue Tracker
* 깃허브 Issue를 모바일로 다루는 앱
* 개발 기간: 22.06.13 ~ 22.07.01

## 프로젝트 소개

깃허브에서 제공하는 이슈 기능을 모바일에서 관리하는 어플리케이션입니다. 

## 🧑‍💻 팀원

|   iOS     |
| ---------- |
|  Chez [@asqw887](https://github.com/asqw887)  |
|  Damagucci-Juice [@Damagucci-Juice](https://github.com/Damagucci-Juice)  |


## 🎯 핵심 기능

- Oauth Github & Apple Login 
- 네트워크 리퀘스트 스타일에 중점
- MVVM을 도입할 때 
    - 뷰와 컴포넌트를 어떻게 나눌까?
    - 객체가 하는 행위의 구체화

## API 요청 흐름

* 로그인 요청 흐름 


## 💡 고민과 해결

<details>
<summary> 
<h3> 💡 네트워크 요청 추상화에 대한 고민(목적 당 리퀘스트 함수가 늘어날 때 관리 편의성) </h3>
</summary>

- AccessToken 요청, Issue 목록 요청 등 여러개의 리퀘스트 함수들이 모두 분리되어 있어 비슷한 역할임에도 중복코드가 늘어나 요청 객체를 편리하게 관리하게 해주려는 시도를 함. 
- [Tiny Network 모델](https://github.com/jdisho/TinyNetworking) 을 참조해 enum case로 request API 들을 관리하고, 요청에 필요한 것들을 case 분기처리를 해서 갖도록 함.
- 요청 객체가 더 필요하게 되면 case 를 더 추가해주면 되고(확장성 증가), enum에서 모든 케이스에 대해 처리를 해주어야 하기 때문에 필요한 값을 모두 셋팅해 주어야 된다는 장점이 있음. 

</details>


<details>
<summary> 
<h3> 💡 사용자의 민감한 정보를 어떻게 관리를 하면 좋을지에 대한 고민 </h3>
</summary>
    
- 키체인을 사용하려다가 구현의 어려움 및 앱을 삭제해도 데이터가 남는다는 이야기를 듣고 XCconfig 를 사용하기로 결정했습니다. 
    
- 문제점이 있었는데, 하나는 GitHub 에 민감정보 파일을 업로드하지 못하기 때문에 다른 작업자가 앱을 실행하지 못하는 문제점이 있었습니다.
    
- 작업자들 끼리 서로 필요한 것(xcconfig)들을 Readme에 명시를 해두고, 작업 진행 상황을 공유할 필요성을 느꼈습니다. 
- 구글링 후 프로젝트 Scheme 도 추가하고 XCConfig 도 추가해서 해결하였습니다.
- 그 중에 추가한 Scheme 이 적용이 안되는 문제가 있었는데 , 프로젝트에 Build Settings 에서 active compilation Conditions 에 추가해주었더니 문제가 해결되었습니다.
- 빌드 시 배포,개발 등 여러 스킴에 대해 다른 XCconfig 설정을 해주어, 개발 환경을 다르게 셋팅 할 수 있다는 것을 배웠습니다. 

</details>

<details>
<summary> 
<h3> 💡 HTTP Request 에 대한 이해 부족에 따른 Request 요청 실패  </h3>
</summary>
    
![Header 관리](https://user-images.githubusercontent.com/50472122/174515144-ab0e3676-23db-408a-90ae-220165d30c1c.png)
    
- HTTP Header 에서 Content-Type Header 와 Accept Header 이용시 어려움    
- URL Request Header 에 Content-Type 을 담아줘야 하는데 그 부분을 이해를 못했습니다. 
- Content-Type 은 Request 메시지와 Response 메시지 모두에 담겨야 한다는 것을 배웠습니다.
- Accept-Type 은 Request 메시지에 담기는데 Response 받을 데이터의 포맷을 지정하는 효과가 있다는 것을 배웠습니다. 

</details>

<details>
<summary> 
<h3> 💡 MVVM 구조에서 객체간의 바인딩을 어떻게하면 좋을지에 대한 고민  </h3>
</summary>
    - 추후 객체간 활동이 명확해지기 전에 이 부분에 대해 의논할 예정입니다.(6.20)
</details>
