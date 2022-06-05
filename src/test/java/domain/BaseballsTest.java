package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseballsTest {

    @Nested
    @DisplayName("Baseballs 생성자는")
    class Describe_Baseballs{

        @Nested
        @DisplayName("숫자의 범위가 1~9인 서로다른 세 개의 숫자가 주어진 경우")
        class Context_with_three_different_numbers_that_have_range_from_1_to_9{
            List validBaseballs = Arrays.asList(4, 2, 5);

            @Test
            @DisplayName("Baseballs를 반환한다.")
            void it_returns_Baseballs() {
                Baseballs actual = new Baseballs(validBaseballs);

                assertThat(actual).isInstanceOf(Baseballs.class);
            }
        }

        @Nested
        @DisplayName("중복된 숫자가 있을 경우")
        class Context_with_duplicate_nubmers{
            List<Integer> duplicateNubmers = Arrays.asList(3, 2, 2);

            @Test
            @DisplayName("IllegalArgumentException 예외를 던진다.")
            void it_thorws_IllegalArgumentException() {
                assertThatThrownBy(() -> new Baseballs(duplicateNubmers))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("숫자의 범위가 1~9가 아닌 경우")
        class Context_with_invalid_range{
            List<Integer> duplicateNubmers = Arrays.asList(0, 5, 7);

            @Test
            @DisplayName("IllegalArgumentException 예외를 던진다.")
            void it_thorws_IllegalArgumentException() {
                assertThatThrownBy(() -> new Baseballs(duplicateNubmers))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("숫자의 갯수가 3개가 아닌 경우")
        class Context_with_invalid_size{

            @ParameterizedTest
            @DisplayName("IllegalArgumentException 예외를 던진다.")
            @MethodSource("domain.BaseballsTest#testWithInvalidBaseballsSize")
            void it_thorws_IllegalArgumentException(List<Integer> baseballs) {
                assertThatThrownBy(() -> new Baseballs(baseballs))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    static Stream<List<Integer>> testWithInvalidBaseballsSize() {
        return Stream.of(
                Arrays.asList(4, 2, 5, 6),
                Arrays.asList(4, 2));
    }
}
