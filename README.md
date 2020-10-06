# Spring Websocket을 이용한 Chatting System 개발

## WebSocket이란?
- 기존 HTTP 통신의 단점을 보완한 지속적 연결 방식의 통신 방식
- 클라이언트와 서버가 실시간으로 데이터를 주고 받을 수 있다.
- 실시간 통신이 필요하고 자주 통신이 필요한 상황에 적합하다.
- Spring Websocket은 이런 websocket 기술을 구현해 준 프레임워크이다.  
  간단한 설정으로 웹소켓을 이용한 통신 방식을 구현할 수 있도록 해준다.


## 코드 설명
### ChatMessage.java
도메인 클래스. 메시지 타입, 보낸 사람, 메시지 내용으로 구성.

### WebSocketConfig.java
웹소켓에 대한 설정이 들어간다.  
STOMP Entrypoint를 설정해주고, 메시지를 보낼 때의 경로, 채널 구독의 경로를 설정해준다. 

### DefaultController.java
메시지를 받았을 때의 행위를 구현한다.  
전달받은 메시지 본문을 @SendTo에 해당하는 채널로 보내서 다른 유저들이 받아볼 수 있도록 한다.

### WebSocketEventListener.java
웹소켓에 관련된 이벤트를 받아서 처리해줄 수 있다.
여기에서는 연결/해제 의 두 가지 이벤트를 받아서 로그로 남기고, 
해제의 경우 다시 채널로 연결해제 메시지를 보낸다.

### main.js
socket.js, stomp client를 가지고 실질적인 통신의 처리를 담당하는 곳이다.
사실 서버쪽 로직은 그렇게 어렵지 않은데 이쪽의 구현이 조금 복잡하다.(어려워 보이지는 않는다.)
클라이언트를 가지고 실시간 채팅창을 구현해주는 페이지 로직을 모두 담고 있다.

## 코드 출처 및 참고
- https://ratseno.tistory.com/72?category=773803