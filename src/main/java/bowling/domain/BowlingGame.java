package bowling.domain;

import java.util.List;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player, Frames frames) {
//        validate(player, frames);
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGame create(Player player) {
        return new BowlingGame(player, Frames.initialize());
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public boolean isRunning() {
        return !frames.isFinalFrameEnd();
    }

    public void bowl(Pins pins) {
        frames.bowl(pins);
    }

    public String playerName() {
        return player.name();
    }

    public List<Frame> frames() {
        return frames.frames();
    }
}
