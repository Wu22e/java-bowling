package bowling.domain.state;

import bowling.domain.Pins;

public class FirstBowl implements FrameState {
    private static final String GUTTER_SYMBOL = "-";

    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public FrameState bowl(Pins hitPins) {
        if (firstPins.isSpare(hitPins)) {
            return new Spare(firstPins);
        }
        return new Miss(firstPins, hitPins);
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public String symbol() {
        if (firstPins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(firstPins.hitPins());
    }
}
