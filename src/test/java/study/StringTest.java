package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html
 */
public class StringTest {

    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    /**
     * 요구사항 1
     */
    @Nested
    class split {

        /**
         * "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
         * 배열로 반환하는 값의 경우 assertj의 contains()를 활용해 반환 값이 맞는지 검증한다.
         *
         * Verifies that the actual iterable/array contains the given values in any order
         */
        @Test
        void contains() {
            String given = "1,2";

            String[] actuals = given.split(",");

            assertThat(actuals).contains("1", "2");
        }

        /**
         * "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
         * 배열로 반환하는 값의 경우 assertj의 containsExactly()를 활용해 반환 값이 맞는지 검증한다.
         *
         * Verifies that the actual group contains exactly the given values and nothing else, in order.
         * https://www.javadoc.io/doc/org.assertj/assertj-core/latest/org/assertj/core/api/AbstractIterableAssert.html#containsExactly(ELEMENT...)
         */
        @Test
        void containsExactly() {
            String given = "1";

            String[] actuals = "1".split(",");

            assertThat(actuals).containsExactly("1");
        }
    }

    /**
     * 요구사항 2
     *
     * "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
     */
    @Test
    void substring() {
        String given = "(1,2)";

        String actual = given.substring(1,4);

        assertThat(actual).contains("1,2");
        assertThat(actual).isEqualTo("1,2");
        assertThat(actual).doesNotContain("(1,2)");
    }

    /**
     * 요구사항 3
     *
     * "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
     * String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.
     * JUnit의 @DisplayName을 활용해 테스트 메소드의 의도를 드러낸다.
     */
    @Nested
    @DisplayName("charAt 메소드는")
    class Describe_charAt{
        String given = "abc";

        @Nested
        @DisplayName("유효한 범위의 인덱스일 경우")
        class Context_string_index_in_bounds{

            @Test
            @DisplayName("인덱스에 해당하는 문자를 반환한다")
            void it_returns_index_value() {
                assertThat(given.charAt(0)).isEqualTo('a');
                assertThat(given.charAt(1)).isEqualTo('b');
                assertThat(given.charAt(2)).isEqualTo('c');
            }
        }

        @Nested
        @DisplayName("유효범위 밖의 인덱스일 경우")
        class Context_string_index_out_of_bounds{
            int invalidIndex = 300;

            @Test
            @DisplayName("StringIndexOutOfBounds예외를 던진다")
            void it_throws_StringIndexOutOfBoundsException() {
                assertThatThrownBy(() -> given.charAt(invalidIndex))
                        .isInstanceOf(StringIndexOutOfBoundsException.class);
            }
        }
    }
}
