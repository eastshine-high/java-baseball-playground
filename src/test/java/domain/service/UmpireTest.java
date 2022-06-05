package domain.service;

import domain.Baseballs;
import domain.VerdictType;
import domain.Verdicts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class UmpireTest {
    private Umpire umpire;

    @BeforeEach
    void setUp() {
        umpire = new Umpire();
    }

    /**
     * e.g. 상대방(컴퓨터)의 수가 425일 때,
     * 456을 제시한 경우 : 1볼 1스트라이크
     */
    @Test
    void testJudgeBaseballsCase1() {
        Baseballs pitchedBaseballs = new Baseballs(Arrays.asList(4, 2, 5));
        Baseballs hitBaseballs = new Baseballs(Arrays.asList(4, 5, 6));

        Verdicts actuals = umpire.judgeBaseballs(pitchedBaseballs, hitBaseballs);

        assertThat(actuals.getCount(VerdictType.STRIKE)).isEqualTo(1);
        assertThat(actuals.getCount(VerdictType.BALL)).isEqualTo(1);
        assertThat(actuals.getCount(VerdictType.NOTHING)).isEqualTo(1);
    }

    /**
     * e.g. 상대방(컴퓨터)의 수가 425일 때,
     * 123을 제시한 경우 : 1스트라이크
     */
    @Test
    void testJudgeBaseballsCase2() {
        Baseballs pitchedBaseballs = new Baseballs(Arrays.asList(4, 2, 5));
        Baseballs hitBaseballs = new Baseballs(Arrays.asList(1, 2, 3));

        Verdicts actuals = umpire.judgeBaseballs(pitchedBaseballs, hitBaseballs);

        assertThat(actuals.getCount(VerdictType.STRIKE)).isEqualTo(1);
        assertThat(actuals.getCount(VerdictType.NOTHING)).isEqualTo(2);
    }
}
