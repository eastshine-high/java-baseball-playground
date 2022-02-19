package dto;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserInputStringTest {

    @Nested
    class UserInputString_생상자는{

        @Nested
        class 입력한_문자열이_3자리_정수로_이루어졌을_경우{
            String validString = "123";

            @Test
            void UserInputString의_인스턴스를_생성한다() {
                assertThat(new UserInputString(validString)).isInstanceOf(UserInputString.class);
            }
        }

        @Nested
        class 입력한_문자열이_정수로_이루어지지_않았을_경우{
            String invalidString = "abc";

            @Test
            void IllegalArgumentException을_던진다() {
                System.out.println(invalidString.length());
                assertThatThrownBy(() -> new UserInputString(invalidString))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        class 입력한_문자열이_3자리_글자로_이루어지지_않았을_경우{
            String invalidString = "1234";

            @Test
            void IllegalArgumentException을_던진다() {
                assertThatThrownBy(() -> new UserInputString(invalidString))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }
}
