package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class VerdictsTest {

    @Nested
    class passed_메소드는{
        Verdicts verdicts;
        HashMap<VerdictType, Integer> map = new HashMap<>();

        @Test
        @DisplayName("3 스트라이크일 경우, true를 반환한다.")
        void testWithRightCondition() {
            map.put(VerdictType.STRIKE, 3);
            verdicts = new Verdicts(map);

            assertThat(verdicts.isPassed()).isTrue();
        }

        @Test
        @DisplayName("3 스트라이크가 아닌 경우, false를_반환한다")
        void testWithWrongCondition() {
            map.put(VerdictType.STRIKE, 2);
            verdicts = new Verdicts(map);

            assertThat(verdicts.isPassed()).isFalse();
        }
    }

    @Test
    void report() {
        Map<VerdictType, Integer> map = new HashMap<>();
        map.put(VerdictType.STRIKE, 1);
        map.put(VerdictType.BALL, 1);

        Verdicts verdicts = new Verdicts(map);

        assertThat(verdicts.report()).contains("1스트라이크 1볼");
    }
}
