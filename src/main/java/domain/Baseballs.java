package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1에서 9까지 서로 다른 수 3개, 일급 컬렉션.
 */
public class Baseballs {
    public static final int BASEBALLS_SIZE = 3;
    public static final int LOWER_LIMIT = 1;
    public static final int UPPER_LIMIT = 9;
    public static final String ERROR_ONLY_THREE_NUMBERS = "숫자는 3개만 입력할 수 있습니다.";
    public static final String ERROR_NUMBERS_DUPLICATE = "숫자들은 중복될 수 없습니다.";
    public static final String ERROR_NUMBER_RANGE = "숫자의 범위는 1~9입니다.";

    private final List<Integer> baseballs;

    public Baseballs(List<Integer> baseballs) {
        validateSize(baseballs);
        validateDuplicate(baseballs);
        validateRange(baseballs);

        this.baseballs = baseballs;
    }

    private void validateSize(List<Integer> baseballs) {
        if (baseballs.size() != BASEBALLS_SIZE) {
            throw new IllegalArgumentException(ERROR_ONLY_THREE_NUMBERS);
        }
    }

    private void validateDuplicate(List<Integer> baseballs) {
        Set<Integer> nonDuplicateNubmers = new HashSet<>(baseballs);
        if (nonDuplicateNubmers.size() != BASEBALLS_SIZE) {
            throw new IllegalArgumentException(ERROR_NUMBERS_DUPLICATE);
        }
    }

    private void validateRange(List<Integer> baseballs) {
        if(baseballs.stream().anyMatch(
                number ->
                        number < LOWER_LIMIT || number > UPPER_LIMIT)){
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        }
    }

    public int size() {
        return baseballs.size();
    }

    public int getBaseball(int position) {
        return baseballs.get(position);
    }

    public boolean contains(int baseball) {
        return baseballs.contains(baseball);
    }
}
