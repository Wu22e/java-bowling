package bowling.domain.state;

import bowling.domain.Pins;

public interface FrameState {
    FrameState bowl(Pins hitPins);

    boolean isFrameEnd();

    String symbol();
}
