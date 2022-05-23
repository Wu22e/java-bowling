package bowling.domain.state;

import bowling.domain.Pins;

public class Miss implements FrameState {
    private final String SYMBOL_FORMAT = "%s|%s";
    private final String GUTTER_SYMBOL = "-";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public FrameState bowl(Pins hitPins) {
        return null;
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public String symbol() {
        return String.format(SYMBOL_FORMAT, gutterOrValue(firstPins), gutterOrValue(secondPins));
    }

    private String gutterOrValue(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(pins.hitPins());
    }
}
