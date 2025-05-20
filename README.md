# Blind-clone (진행중)

## 설명
블라인드 클론 프로젝트 입니다. Firebase Realtime Database에 게시글을 저장하고 불러 올 수 있습니다. 
게시판을 Compose - Flow로 구현하였고 안드로이드 추가 라이브러리를 사용하여 Flow를 앱 라이프 사이클에 맞게 구현하여 리소스 낭비를 하지 않도록 구현하였습니다.

## 화면
| Home                                    | Write                                    |
|-----------------------------------------|------------------------------------------|
| <img src="./images/home.png" width=150> | <img src="./images/write.png" width=150> |

## 구현 기능
- 실시간 데이터베이스 데이터 동기화 (Firebase Realtime Database)

## 기술 스택
Language : Kotlin <br>
View : Compose <br>
AndroidX : Room, ViewModel, Hilt, AndroidX-Flow-Lifecycle <br>
Kotlin : Coroutine, StateFlow <br>
etc : Firebase Realtime Database, Retrofit, OkHttp <br>
