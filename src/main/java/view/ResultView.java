package view;

import domain.Verdicts;

/**
 * 출력을 담당한다.
 */
public class ResultView {
    public static final String GAME_COMPLETE_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

    /*
     * 오류를 출력한다.
     */
    public void printExceptionMessage(Exception ex) {
        System.out.println(ex.getMessage());
    }

    /*
     * 게임 결과를 출력한다.
     */
    public void showInningResult(Verdicts verdicts) {
        System.out.println(verdicts.report());
    }

    /*
     * 게임이 완수되었음을 알린다.
     */
    public void noticeGameCompleted() {
        System.out.println(GAME_COMPLETE_MESSAGE);
    }
}
