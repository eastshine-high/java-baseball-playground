# 숫자야구게임 구현

## 목차

- 프로그래밍 요구사항
- 기능 요구 사항
- 실행 결과
- 객체 설계
- 기능 목록
- [회고](#retrospective)

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

## 객체 설계
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
    │   ├── Hitter.java
    │   ├── Pitcher.java
    │   └── Umpire.java
    ├── dto
    │   └── UserInputString.java
    └── view
        ├── InputView.java
        └── ResultView.java
```

`domain`(야구 도메인의 용어를 사용)

- `Baseballs` - '순서가 있는 1에서 9까지 서로 다른 임의의 수 3개'를 표현하는 일급 컬렉션.
- `Verdicts` - '판정 결과들'을 표현하는 일급 컬렉션.
- `Pitcher` - 숫자 야구 게임에서의 '컴퓨터' 역할.
- `Hitter` - 숫자 야구 게임에서의 '플레이어' 역할.
- `Umpire` - 컴퓨터와 플레이어 입력 값의 판정을 담당.

`application`

- `GameService` - 도메인 계층의 객체들과 협력하여 야구 게임을 진행합니다.

`controller`

- `GameController` - 게임 전체를 컨트롤합니다. UI에서 받은 입력을 응용 계층에 처리를 요청한 뒤, 처리 결과를 UI에 전달합니다.

`dto`

- `UserInputString` - 사용자 입력 문자열(숫자로만 이뤄진 길이가 3인 문자열)을 추상화.

`view`

- `InputView` - 게임의 입력 UI를 담당.
- `ResultView` - 게임의 출력 UI를 담당.

`config`

- `GameContext` - 의존성의 생성과 주입을 담당.

## 기능 목록

- [x] 게임 의존성 생성 및 주입 - `config/GameContext#getGameController()`
- [x] 게임 실행 - `controller/GameController#run()`
  - [x] 게임 초기화 - `application/GameService#initialize()`
    - [x] 1~9의 서로 다른 수 3개를 생성하여 반환 - `domain/Pitcher#pitch()`
    - [x] '컴퓨터의 1~9의 서로 다른 수 3개'인 `Baseballs.pitchedBaseballs` 필드를 초기화
  - [x] **판정이 통과할 때까지 게임을 진행한다** - `./GameController#playGame()`
  - [x] 게임을 완수했음을 알린다 - `view/ResultView#noticeGameCompleted()`
  - [x] 게임 재시작 여부를 묻는다 -`view/inputView#isRestart()`

- [x] **판정이 통과할 때까지 게임을 진행한다** - `controller/GameController#playGame()`
  - [x] 사용자로부터 문자열을 입력받아 '사용자 입력 문자열(`dto/UserInputString`)'을 반환 - `view/InputView#askUserInputString()`
  - [x] '사용자 입력 문자열(`dto/UserInputString`)'을 판정하여 '판정 결과(`domain/Verdicts` )'룰 반환 - `application/GameService#playInning()`
    - [x] 'DTO(`dto/UserInputString`)'를 '도메인 객체(`domain/Baseballs`)'로 변환하여 반환 - `domain/Hitter#hit()`
    - [x] 컴퓨터 입력 숫자들(`Baseballs.pitchedBaseballs`)과 사용자 입력 숫자들(`Baseballs.hitBaseballs`)을 비교하여 '판정 결과(`domain/Verdicts`)'를 반환 - `domain/Umpire#judgeBaseballs()`
      - [x] 특정 위치의 숫자를 판정 - `./Umpire#judgeBaeeball()`
        - [x] 특정 위치에 같은 숫자가 있을 경우, '스트라이크(`VerdictType.STRIKE`)'이다. - `./Umpire#isStrike()`
        - [x] '스트라이크'가 아니고 다른 위치에 같은 숫자가 있는 경우, '볼(`VerdictType.BALL`)'이다. '볼'이 아닌 경우, '낫싱(`VerdictType.NOTHING`)'이다. - `./Umpire#isBall()`
  - [x] 게임 결과를 출력 - `view/ResultView#showInningResult()`
    - [x] 판정 결과 보고 - `domain/Verdicts#report()`
  - [x] 게임 통과 여부를 확인 - `domain/Verdicts#isPassed()`

## 회고 <a name ="retrospective"></a>

프레임워크를 이용하지 않고 순수한 Java를 이용해 요구사항을 구현할 때, 처음 들었던 감정은 ‘혼란’이었습니다. 지금 내가 설계하고 개발하는 방법이 올바른 방법인지를 알 수 없었기 때문입니다.

‘무엇이 올바른 방법인가’에 대한 고민은 [조영호님의 저서 오브젝트](https://github.com/eastshine-high/til/tree/main/books/object) 를 통해 해소할 수 있었습니다. 책은 정확히 제가 고민하던 부분에 대해 논하고 있었습니다.

> 좋은 설계란 무엇인가? 우리가 짜는 프로그램은 두 가지 요구사항을 만족시켜야 한다. 오늘 완성해야 하는 기능을 구현하는 코드를 짜야 하는 동시에 내일 쉽게 변경할 수 있는 코드를 짜야한다[Sandi Metz].
>

변경에 유연하게 대응하는 코드를 위한 방법 중의 하나는 객체지향 프로그래밍입니다. 다소 추상적으로만 알고 있던 객체지향 프로그래밍은 책 오브젝트를 통해서 구체적인 코드와 함께 이해할 수 있었습니다. 특히 3장 [협력, 책임, 역할](https://github.com/eastshine-high/til/blob/main/books/object/collaboration-responsibility-role.md) 을 통해 ‘객체지향의 본질이 협력하는 객체들의 공동체를 창조하는 것’임을 깨달았습니다. 그리고 5장을 통해 [설계 품질](https://github.com/eastshine-high/til/blob/main/books/object/design-quality.md) 을 평가하는 기준들을 배우고 8-9장을 통해 협력하는 객체 사이의 의존성을 적절하게 관리하는 것을 학습하였습니다.

요구사항을 개발할 때는, 학습한 내용을 적용해 보려 노력하였습니다. 예를 들어, 8-9장의 의존성 관리 부분을 적용하여 ‘의존성의 생성과 주입’을 담당하는 [GameContext](https://github.com/eastshine-high/java-baseball-playground/blob/main/src/main/java/config/GameContext.java) 객체를 두었습니다. 그리고 3장 협력, 책임, 역할을 적용하여 관심사 분리를 고민해 보며 프로그램을 구현해 나갔습니다. 최종적으로 프로그램의 구조가 도메인 주도 설계의 [LAYERED ARCHITECTURE](https://github.com/eastshine-high/til/blob/main/books/domain-driven-design/isolating-the-domain/layered-architecture.md) 와 유사한 형태를 띄면서, 객체지향 프로그래밍을 어느 정도 이해했다는 성취감을 얻을 수 있었습니다.
