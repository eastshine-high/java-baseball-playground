package domain;

import domain.Baseballs;
import domain.VerdictType;
import domain.Verdicts;

import java.util.HashMap;
import java.util.Map;

/**
 * 컴퓨터와 사용자의 입력 값을 판정한다.
 */
public class Umpire {
    private Baseballs pitchedBaseballs;
    private Baseballs hitBaseballs;

    /**
     * '컴퓨터 입력 숫자들'과 '사용자 입력 숫자들'을 비교하여 '판정 결과'를 반환한다.
     *
     * @param pitchedBaseballs 컴퓨터가 생성한 3자리 숫자.
     * @param hitBaseballs 사용자가 입력한 3자리 숫자.
     * @return 판정 결과
     */
    public Verdicts judgeBaseballs(Baseballs pitchedBaseballs, Baseballs hitBaseballs) {
        this.pitchedBaseballs = pitchedBaseballs;
        this.hitBaseballs = hitBaseballs;

        Map<VerdictType, Integer> verdicts = new HashMap<>();
        for (int i = 0; i < pitchedBaseballs.size(); i++) {
            VerdictType verdict = judgeBaseball(i);
            verdicts.merge(verdict, 1, Integer::sum);
        }

        return new Verdicts(verdicts);
    }

    /**
     * 특정 위치에 있는 숫자에 대한 판정 값을 반환한다.
     *
     * @param position 숫자의 위치
     * @return 판정 값
     */
    private VerdictType judgeBaseball(int position) {
        if (isStrike(position)) {
            return VerdictType.STRIKE;
        }

        if(isBall(hitBaseballs.getBaseball(position))){
            return VerdictType.BALL;
        }

        return VerdictType.NOTHING;
    }

    private boolean isStrike(int position){
        if (pitchedBaseballs.getBaseball(position) == hitBaseballs.getBaseball(position)) {
            return true;
        }
        return false;
    };

    /**
     * BALL의 여부를 판정하여 반환한다. pitchedBaseballs에 hitBaseball이 포함된 경우 true, 포함되지 않을 경우 false.
     *
     * @param hitBaseball 판정할 숫자 공 하나
     * @return pitchedBaseballs에 hitBaseball이 포함된 경우 true, 포함되지 않을 경우 false.
     */
    private boolean isBall(int hitBaseball) {
        if(pitchedBaseballs.contains(hitBaseball)){
            return true;
        }
        return false;
    }
}
