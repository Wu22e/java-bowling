package bowling.domain.state;

import bowling.domain.Pins;

public class Strike implements FrameState{
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
        return "X";
    }
}
