package domain.service;

import domain.service.Pitcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PitcherTest {
    private Pitcher pitcher;

    @BeforeEach
    void setUp() {
        pitcher = new Pitcher();
    }

    @Nested
    class pitch_메서드는{

        @RepeatedTest(10)
        void _1에서_9까지_서로_다른_임의의_수_3개를_생성한다() {
            List<Integer> pitchedBaseballs = pitcher.pitch();

            assertThat(new HashSet(pitchedBaseballs)).hasSize(3);
            assertThat(pitchedBaseballs).allSatisfy(number -> {
                assertThat(number).isGreaterThanOrEqualTo(1);
                assertThat(number).isLessThan(10);
            });
        }
    }
}
