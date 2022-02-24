package domain;

import java.util.Map;

/**
 * 판정 결과.
 */
public class Verdicts {
    public static final int FULL_COUNT = 3;
    public static final int ZERO_COUNT = 0;

    private final Map<VerdictType, Integer> verdicts;

    public Verdicts(Map<VerdictType, Integer> verdicts) {
        this.verdicts = verdicts;
    }

    /**
     * 판정 결과를 보고한다.
     *
     * @return 판정 결과 보고.
     */
    public String report() {
        if(isFourBall()) return VerdictType.FOUR_BALL.getName();

        StringBuffer result = new StringBuffer();
        if(haveStrike()) result.append(
                String.format("%d%s ", getCount(VerdictType.STRIKE), VerdictType.STRIKE.getName())
        );
        if(haveBall()) result.append(
                String.format("%d%s", getCount(VerdictType.BALL), VerdictType.BALL.getName())
        );
        return result.toString();
    }

    public int getCount(VerdictType verdictType) {
        return verdicts.getOrDefault(verdictType, 0);
    }

    private boolean isFourBall() {
        return getCount(VerdictType.NOTHING) == FULL_COUNT;
    }

    private boolean haveStrike() {
        return getCount(VerdictType.STRIKE) > ZERO_COUNT;
    }

    private boolean haveBall() {
        return getCount(VerdictType.BALL) > ZERO_COUNT;
    }

}
