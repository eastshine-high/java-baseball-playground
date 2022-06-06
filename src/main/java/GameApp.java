import controller.GameController;
import config.GameContext;

public class GameApp {

    public static void main(String[] args) {
        GameController gameController = GameContext.getGameController();
        gameController.run();
    }
}
