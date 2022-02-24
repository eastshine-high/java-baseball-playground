package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class VerdictsTest {

    @Test
    void report() {
        Map<VerdictType, Integer> map = new HashMap<>();
        map.put(VerdictType.STRIKE, 1);
        map.put(VerdictType.BALL, 1);

        Verdicts verdicts = new Verdicts(map);

        assertThat(verdicts.report()).contains("1스트라이크 1볼");
    }
}
