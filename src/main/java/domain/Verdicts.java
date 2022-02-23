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

    public int getCount(VerdictType verdictType) {
        return verdicts.getOrDefault(verdictType, 0);
    }
}
