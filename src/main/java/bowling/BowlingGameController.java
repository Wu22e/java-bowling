package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingGameController {
    public static void main(String[] args) {
        Player player = new Player(InputView.scanPlayerName());
        BowlingGame bowlingGame = BowlingGame.create(player);
        while (bowlingGame.isRunning()) {
            bowlingGame.bowl(InputView.scanHitPins());
        }
    }
}
