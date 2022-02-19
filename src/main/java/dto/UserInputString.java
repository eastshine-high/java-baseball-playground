package dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 사용자 입력 문자열.
 */
public class UserInputString {
    private static final int USER_INPUT_STRING_LENGTH = 3;
    private static final String ERROR_ONLY_THREE_NUMBERS = "3자리 숫자만 입력 가능합니다.";
    private static final Pattern NON_NUMERIC_FORMAT = Pattern.compile("\\D");

    private final String userInputString;

    public UserInputString(String userInputString) {
        validateThreeLength(userInputString);
        validateInteger(userInputString);

        this.userInputString = userInputString;
    }

    /**
     * 입력 값의 길이가 3인지를 검증.
     *
     * @param userInputString 사용자 입력 문자열.
     */
    private void validateThreeLength(String userInputString) {
        if (userInputString.length() != USER_INPUT_STRING_LENGTH) {
            throw new IllegalArgumentException(ERROR_ONLY_THREE_NUMBERS);
        }
    }

    /**
     * 입력 값이 정수로 이뤄진 문자열임을 검증.
     *
     * @param userInputString 사용자 입력 문자열.
     */
    private void validateInteger(String userInputString) {
        Matcher matcher = NON_NUMERIC_FORMAT.matcher(userInputString);
        if(matcher.find()) throw new IllegalArgumentException(ERROR_ONLY_THREE_NUMBERS);
    }

    public String getValue() {
        return userInputString;
    }
}
