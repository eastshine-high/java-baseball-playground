package domain;

import domain.Baseballs;
import dto.UserInputString;

import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 입력 값 변환기.
 */
public class Hitter {

    /**
     * '사용자 입력 문자열'을 '1~9의 서로 다른 3자리의 수'로 변환하여 반환한다.
     *
     * @param userInputString 사용자 입력 문자열.
     * @return 1~9의 서로 다른 3자리의 수.
     */
    public Baseballs hit(UserInputString userInputString) {
        String[] baseballs = userInputString.getValue().split("");

        List<Integer> baseballList = new ArrayList();
        for(int i = 0; i < baseballs.length; i++) {
            baseballList.add(Integer.valueOf(baseballs[i]));
        }
        return new Baseballs(baseballList);
    }
}
