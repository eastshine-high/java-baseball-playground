package domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 숫자 야구 게임에서 상대방의 역할을 담당한다.
 */
public class Pitcher {
    private static final int PITCHED_BASEBALLS_SIZE = 3;
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 9;

    /**
     * 1에서 9까지 서로 다른 임의의 수 3개를 생성하여 반환한다.
     *
     * @return 1에서 9까지 서로 다른 임의의 수 3개
     */
    public List<Integer> pitch() {
        Set<Integer> pitchedBaseballs = new LinkedHashSet<>();
        Random random = new Random();

        while(pitchedBaseballs.size() < PITCHED_BASEBALLS_SIZE){
            pitchedBaseballs.add(
                    random.nextInt(END_NUMBER) + START_NUMBER
            );
        }

        return new ArrayList<Integer>(pitchedBaseballs);
    }
}
