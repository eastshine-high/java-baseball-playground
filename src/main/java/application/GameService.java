package application;

import domain.Baseballs;
import domain.service.Hitter;
import domain.service.Pitcher;
import domain.service.Umpire;
import domain.Verdicts;
import dto.UserInputString;

import java.util.List;

// 야구 게임의 서비스 레이어
public class GameService {
    private final Pitcher pitcher;
    private final Hitter hitter;
    private final Umpire umpire;

    private Baseballs pitchedBaseballs;

    public GameService(Pitcher pitcher, Hitter hitter, Umpire umpire) {
        this.pitcher = pitcher;
        this.hitter = hitter;
        this.umpire = umpire;
    }

    /**
     * 컴퓨터가 생성한 숫자(Baseballs.pitchedBaseballs)를 초기화한다.
     */
    public void initialize() {
        List<Integer> baseballList = pitcher.pitch();
        this.pitchedBaseballs = new Baseballs(baseballList);
    }

    /**
     * '사용자 입력 문자열'을 판정하여 '판정 값'을 반환한다.
     *
     * @param userInputString 사용자 입력 문자열.
     * @return 판정 값.
     */
    public Verdicts playInning(UserInputString userInputString) {
        Baseballs hitBaseballs = hitter.hit(userInputString);
        return umpire.judgeBaseballs(this.pitchedBaseballs, hitBaseballs);
    }
}
