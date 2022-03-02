import controller.GameController;
import domain.service.Hitter;
import domain.service.Pitcher;
import domain.service.Umpire;
import application.GameService;
import view.InputView;
import view.ResultView;

public class GameApp {

    /**
     * 의존성을 주입한 뒤, controller를 호출한다.
     */
    public static void main(String[] args) {
        GameController gameController = new GameController(
                new InputView(),
                new ResultView(),
                new GameService(
                        new Pitcher(),
                        new Hitter(),
                        new Umpire())
        );

        gameController.run();
    }
}
