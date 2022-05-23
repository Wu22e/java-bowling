package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.List;

public class OutputView {
    private static final String FRAME_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_FRAME = "|   %s |";
    private static final String SYMBOL_FRAME = "   %s |";
    private static final String BLANK = " ";

    private OutputView() {
    }

    public static void printBowlingGameResult(BowlingGame bowlingGame) {
        printFrameHeader();
        printPlayerAndScoreSymbols(bowlingGame);
    }

    private static void printFrameHeader() {
        System.out.println(FRAME_HEADER);
    }

    private static void printPlayerAndScoreSymbols(BowlingGame bowlingGame) {
        printPlayerName(bowlingGame.playerName());
        printScoreSymbols(bowlingGame.frames());
    }

    private static void printPlayerName(String playerName) {
        System.out.printf(String.format(PLAYER_FRAME, playerName));
    }

    public static void printScoreSymbols(List<Frame> frames) {
        frames.stream().map(frame -> {
            return System.out.printf(String.format(SYMBOL_FRAME, frame.symbol()));
        });
    }
}
