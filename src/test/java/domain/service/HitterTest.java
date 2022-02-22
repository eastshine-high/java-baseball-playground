package domain.service;

import domain.Baseballs;
import dto.UserInputString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HitterTest {

    @Test
    @DisplayName("hit 메소드는 UserInputString을 Baseballs로 변환하여 반환한다.")
    void hit() {
        Hitter hitter = new Hitter();

        assertThat(hitter.hit(new UserInputString("123"))).isInstanceOf(Baseballs.class);
    }
}
