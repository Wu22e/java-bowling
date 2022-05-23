package bowling.domain;

import bowling.domain.state.BeforeProgress;
import bowling.domain.state.FrameState;
import bowling.domain.state.Miss;

import java.util.LinkedList;
import java.util.List;

public class FinalFrame implements Frame {
    private final LinkedList<FrameState> frameStates;

    public FinalFrame() {
        this.frameStates = new LinkedList<>(List.of(new BeforeProgress()));
    }

    public static Frame initialize() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(Pins hitPins) {
        FrameState lastFrameState = frameStates.getLast();
        if (lastFrameState.isFrameEnd() && !isMiss(lastFrameState)) {
//            addFrameState(hitPins);
            frameStates.add(new BeforeProgress().bowl(hitPins));
            return this;
        }
//        updateFrameState(hitPins);
        frameStates.removeLast();
        frameStates.add(lastFrameState.bowl(hitPins));
        return this;
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public String symbol() {
        return null;
    }

    public boolean isMiss(FrameState frameState) {
        return frameState instanceof Miss;
    }
}
