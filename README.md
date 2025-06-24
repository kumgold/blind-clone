# Blind-clone
커뮤니티 기능을 중점적으로 공부하기 위해 제작한 프로젝트 입니다. Android-Kotlin으로 개발하였습니다.

## 📖 프로젝트 소개
블라인드 클론 프로젝트 입니다. Firebase Realtime Database에 게시글을 저장하고 불러 올 수 있습니다. 
게시판을 Compose - Flow로 구현하였고 안드로이드 추가 라이브러리를 사용하여 Flow를 앱 라이프 사이클에 맞게 구현하여 리소스 낭비를 하지 않도록 구현하였습니다.

## 🛠 기술 스택
Language : Kotlin <br>
View : Compose <br>
AndroidX : Room, ViewModel, Hilt, AndroidX-Flow-Lifecycle <br>
Kotlin : Coroutine, StateFlow <br>
etc : Firebase Realtime Database, Retrofit, OkHttp <br>

## ✨ 주요 기능
- 실시간 데이터베이스 데이터 동기화 (Firebase Realtime Database)
- 내부 데이터베이스 저장 (Room Database)
- Compose + StateFlow를 사용한 MVI 패턴 사용


## 🏞️ 화면
<img src="./images/app.jpeg" alt="blind clone screen capture">

## 👀 개발 과정에서 발생한 이슈

### 1. 실시간성 데이터 표현 방식
두 가지 방식을 고민했습니다. 첫 번째 방식은 채팅처럼 동작하는 방식으로 Firebase에 저장된 데이터에 변경사항이 생기면 즉시 앱에서 확인할 수 있도록
구현하는 방식입니다. 두 번째 방식은 사용자에게 데이터 갱신에 대한 선택권을 주어 Refresh 로직을 추가하고 내부 데이터베이스를 바라보고 게시판을 업데이트 하는 방식입니다.
처음 프로젝트를 개발할 때, 첫 번째 방식으로 구현하였습니다. 그 이유는 첫 번째 방식으로 앱을 개발한 후 기능을 추가하면 두 번째 방식으로 개발이 가능했기 때문입니다.
기능을 점차 추가하며 고도화 하는 것이 더 적합한 개발 방법론이라고 생각했습니다.

### 2. 프로젝트 아키텍처 및 패턴
클론을 시작한 시점에 서비스 규모가 작기 때문에 간단한 아키텍처로 구현할까 생각했지만,
전반적으로 안드로이드 개발할때 많이 사용하고 큰 프로젝트를 개발하는 경우와 구조를 연습하기 위해서 규모에 비해 복잡한 아키텍처로 구현하였습니다. <br>
아키텍처는 UI Layer - Data Layer를 폴더로 구조화 하여 구성하였습니다. 최근 가장 많이 사용하는 MVI 패턴을 사용하였고, Compose + StateFlow로 구현하였습니다.
Hilt를 사용해서 DI를 구현하였고 Repository를 Interface와 구현체로 구현하였습니다.

## 🎯 개발 계획 
 - 모든 화면 구현
 - 각 화면별로 Post List 필터링 및 Refresh 변경
 - 내부 데이터베이스 연동
