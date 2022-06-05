# 숫자야구게임 구현

## 프로그래밍 요구사항

- **자바 코드 컨벤션을 지키면서 프로그래밍한다.**
  - 기본적으로 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)을 원칙으로 한다.
  - 단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.
- **indent(인덴트, 들여쓰기) depth를 2가 넘지 않도록 구현한다. 1까지만 허용한다.**
  - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
  - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
- **else 예약어를 쓰지 않는다.**
  - 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
  - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
- **모든 로직에 단위 테스트를 구현한다.** 단, UI(System.out, System.in) 로직은 제외
  - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
  - UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.

## 기능 요구 사항

기본적으로 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임이다.

- 같은 수가 같은 자리에 있으면 스트라이크, 다른 자리에 있으면 볼, 같은 수가 전혀 없으면 포볼 또는 낫싱이란 힌트를 얻고, 그 힌트를 이용해서 먼저 상대방(컴퓨터)의 수를 맞추면 승리한다.
  - e.g. 상대방(컴퓨터)의 수가 425일 때, 123을 제시한 경우 : 1스트라이크, 456을 제시한 경우 : 1볼 1스트라이크, 789를 제시한 경우 : 낫싱
- 위 숫자 야구 게임에서 상대방의 역할을 컴퓨터가 한다. 컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다. 게 임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
- 이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
- 게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.

## 실행결과
```
숫자를 입력해 주세요 : 123
1볼 1스트라이크
숫자를 입력해 주세요 : 145
1볼
숫자를 입력해 주세요 : 671
2볼
숫자를 입력해 주세요 : 216
1스트라이크
숫자를 입력해 주세요 : 713
3스트라이크
3개의 숫자를 모두 맞히셨습니다! 게임 종료
게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.
1
숫자를 입력해 주세요 : 123
1볼 1스트라이크
…
```

## 기능 목록
```
─── java
    ├── GameApp.java
    ├── application
    │   └── GameService.java
    ├── controller
    │   └── GameController.java
    ├── domain
    │   ├── Baseballs.java
    │   ├── VerdictType.java
    │   ├── Verdicts.java
    │   └── service
    │       ├── Hitter.java
    │       ├── Pitcher.java
    │       └── Umpire.java
    ├── dto
    │   └── UserInputString.java
    └── view
        ├── InputView.java
        └── ResultView.java

```

- [x] 게임을 실행한다 - `controller/GameController#run`
  - [x] 게임을 초기화한다. - `application/GameService#initialize`
    - [x] 1~9의 서로 다른 수 3개를 생성하여 반환한다. - `domain/service/Pitcher#pitch`
    - [x] '컴퓨터가 생성한 1~9의 서로 다른 수 3개(`Baseballs.pitchedBaseballs`)'를 초기화한다.
  - [x] **판정이 통과할 때까지 게임을 진행 한다.** - `./GameController#playGame`
  - [x] 게임을 완수했음을 알린다. - `view/ResultView#noticeGameCompleted`
  - [x] 게임 재시작 여부를 묻는다. -`view/inputView#isRestart`

- [x] **판정이 통과할 때까지 게임을 진행 한다.** - `controller/GameController#playGame`
  - [x] 사용자로부터 문자열을 입력받아 '사용자 입력 문자열(`dto/UserInputString`)'을 반환한다. - `view/InputView#askUserInputString`
    - [x] '사용자 입력 문자열(`dto/UserInputString`)'은 숫자로만 이뤄진 길이가 3인 문자열이다.
  - [x] '사용자 입력 문자열'을 판정하여 '판정 값(`domain/Verdicts`)'을 반환한다. - `application/GameService#playInning`
    - [x] '사용자 입력 문자열(`dto/UserInputString`)''을 '사용자 입력 1~9의 서로 다른 수 3개(`Baseballs.hitBaseballs`)'로 변환하여 반환한다. - `domain/service/Hitter#hit`
    - [x] 컴퓨터 입력 숫자들(`Baseballs.pitchedBaseballs`)과 사용자 입력 숫자들(`Baseballs.hitBaseballs`)을 비교하여 '판정 결과(`domain/Verdicts`)'를 반환한다. - `domain/service/Umpire#judgeBaseballs`
      - [x] 특정 위치의 숫자를 판정한다. - `./Umpire#judgeBaeeball`
        - [x] 특정 위치에 같은 숫자가 있을 경우, '스트라이크(`VerdictType.STRIKE`)'이다. - `./Umpire#isStrike`
        - [x] '스트라이크'가 아니고 다른 위치에 같은 숫자가 있는 경우, '볼(`VerdictType.BALL`)'이다. '볼'이 아닌 경우, '낫싱(`VerdictType.NOTHING`)'이다. - `./Umpire#isBall`
  - [x] 게임 결과를 출력한다. - `view/ResultView#showInningResult`
    - [x] 판정 결과를 보고한다. - `domain/Verdicts#report`
  - [x] 게임 통과 여부를 확인한다. - `domain/Verdicts#isPassed`


### 도메인 객체(야구 도메인의 용어를 사용)

#### `BaseballNumbers` - 일급 컬렉션, 순서가 있는 1에서 9까지 서로 다른 임의의 수 3개.

#### `Verdicts` - 일급 컬렉션, 판정 결과.

#### `service/Pitcher` - 숫자 야구 게임에서 상대방 역할.

#### `service/Hitter` - 사용자 역할(사용자 입력 값 매퍼).

#### `service/Umpire` - 컴퓨터와 사용자의 입력 값을 판정.
