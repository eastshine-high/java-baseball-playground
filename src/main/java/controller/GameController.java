package controller;

import domain.Verdicts;
import application.GameService;
import view.InputView;
import view.ResultView;
import dto.UserInputString;

public class GameController {
    private final InputView inputView;
    private final ResultView resultView;
    private final GameService gameService;

    public GameController(InputView inputView, ResultView resultView, GameService gameService) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.gameService = gameService;
    }

    /**
     * 게임을 실행한다.
     */
    public void run() {
        boolean isGameOn = true;
        while(isGameOn){
            gameService.initialize();
            playGame();

            resultView.noticeGameCompleted();
            isGameOn = inputView.isRestart();
        }
    }

    /**
     * 판정이 통과할 때까지 게임을 진행 한다.
     */
    private void playGame() {
        boolean passed = false;
        while (!passed) {
            try {
                UserInputString baseballString = inputView.askUserInputString();

                Verdicts verdicts = gameService.playInning(baseballString);
                resultView.showInningResult(verdicts);
                passed = verdicts.isPassed();
            }
            catch (Exception ex) {
                resultView.printExceptionMessage(ex);
            }
        }
    }
}
