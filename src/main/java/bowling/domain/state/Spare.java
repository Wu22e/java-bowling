package bowling.domain.state;

import bowling.domain.Pins;

public class Spare implements FrameState {
    private final String SYMBOL_FORMAT = "%s|%s";
    private final String GUTTER_SYMBOL = "-";
    private final String SPARE_SYMBOL = "/";

    private final Pins firstPins;

    public Spare(Pins firstPins) {
//        validatePins(firstPins);
        this.firstPins = firstPins;
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
        return String.format(SYMBOL_FORMAT, gutterOrValue(firstPins), SPARE_SYMBOL);
    }

    private String gutterOrValue(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(pins.hitPins());
    }
}
