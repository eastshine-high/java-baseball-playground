package config;

import application.GameService;
import controller.GameController;
import domain.Hitter;
import domain.Pitcher;
import domain.Umpire;
import view.InputView;
import view.ResultView;

/**
 * 의존성을 생성하고 주입한다.
 */
public class GameContext {
    private static GameController gameController;

    private GameContext() { }

    public static GameController getGameController() {
        if (gameController == null) {
            gameController = new GameController(
                    new InputView(),
                    new ResultView(),
                    new GameService(
                            new Pitcher(),
                            new Hitter(),
                            new Umpire())
            );
        }

        return gameController;
    }
}
