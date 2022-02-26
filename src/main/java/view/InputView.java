package view;

import dto.UserInputString;

import java.util.Scanner;

/**
 * 입력을 담당한다.
 */
public class InputView {
    private static final int GAME_ON = 1;
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 사용자로부터 문자열을 입력받아 '사용자 입력 문자열'을 반환한다.
     *
     * @return 사용자 입력 문자열.
     */
    public UserInputString askUserInputString() {
        System.out.print("숫자를 입력해 주세요 : ");

        String inputString = SCANNER.nextLine();
        return new UserInputString(inputString);
    }

    /**
     * 사용자로부터 문자열을 입력받아 '재시작 여부'를 반환한다.
     */
    public boolean isRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String inputString = SCANNER.nextLine();

        if (Integer.parseInt(inputString) == GAME_ON) {
            return true;
        }
        return false;
    }
}
