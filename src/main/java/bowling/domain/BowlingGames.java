package bowling.domain;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGames {
    private static final int UNIT_INDEX = 1;
    private static final int START_INDEX = 0;

    private final List<BowlingGame> bowlingGames;
    private int playerIndex;

    public BowlingGames(List<BowlingGame> bowlingGames) {
        validateBowlingGames(bowlingGames);
        this.bowlingGames = bowlingGames;
        this.playerIndex = 0;
    }

    private void validateBowlingGames(List<BowlingGame> bowlingGames) {
        if (bowlingGames == null) {
            throw new IllegalArgumentException("볼링 게임 리스트는 null 일 수 없습니다.");
        }
    }

    public static BowlingGames initialize(List<Player> players) {
        return players.stream()
                .map(BowlingGame::create)
                .collect(collectingAndThen(toList(), BowlingGames::new));
    }

    public void playBowlingGame(Pins hitPins) {
        BowlingGame bowlingGame = getBowlingGame();
        bowlingGame.bowl(hitPins);
        if (bowlingGame.isCurrentFrameEnd()) {
            bowlingGame.updateToNextFrameNumber();
            updatePlayerIndex();
        }
    }

    private void updatePlayerIndex() {
        playerIndex++;
        if (playerNumber() < playerIndex + UNIT_INDEX) {
            playerIndex = START_INDEX;
        }
    }

    private int playerNumber() {
        return bowlingGames.size();
    }

    public boolean isCurrentPlayerName(String playerName) {
        return currentPlayerName().equals(playerName);
    }

    public boolean isRunning() {
        return bowlingGames.stream().anyMatch(BowlingGame::isRunning);
    }

    public String currentPlayerName() {
        return getBowlingGame().playerName();
    }

    public List<BowlingGame> bowlingGames() {
        return bowlingGames;
    }

    private BowlingGame getBowlingGame() {
        return bowlingGames.get(playerIndex);
    }
}