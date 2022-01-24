# Covid19-Map

1. 요구사항

   * Splash
      + API를 통해 1페이지(page)에 10개(perPage) 씩 순서대로 10개 페이지 호출(총 100개)하여 데이터 저장
         - DataStore, ROOM, safeArgs 3가지 방식 중 하나
      + 저장이 완료되면 Map 화면으로 이동
   * Map
      + 마커 생성
         - 저장된 리스트의 데이터를 통해 마커 생성
         - centerType에 따라 마커 색상 구분
      + 마커 클릭
         - 해당 마커의 정보를 alert으로 생성

2. 사용 기술

   * 프로그래밍 언어
      + Kotlin
   * 네트워크 통신
      + Retrofit2
   * 데이터베이스
      + Room
   * 지도 라이브러리
      + Naver Map
   * 비동기 처리
      + Coroutine
   * Jetpack 라이브러리
      + DataBinding
      + ViewModel
      + LiveData
      + Navigation