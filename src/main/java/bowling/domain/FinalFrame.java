package bowling.domain;

import bowling.domain.state.BeforeProgress;
import bowling.domain.state.FrameState;
import bowling.domain.state.Miss;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final String DELIMITER = "|";
    private static final int MAX_BOWL = 3;
    private static final int MIN_BOWL = 2;

    private final LinkedList<FrameState> frameStates;
    private int bowlCount;

    public FinalFrame() {
        this.frameStates = new LinkedList<>(List.of(new BeforeProgress()));
        this.bowlCount = 0;
    }

    public static Frame initialize() {
        return new FinalFrame();
    }

    @Override
    public Frame bowl(Pins hitPins) {
        this.bowlCount++;
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
        if (this.bowlCount == MAX_BOWL) {
            return true;
        }
        return this.bowlCount == MIN_BOWL && isMiss(frameStates.getLast());
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public String symbol() {
        return frameStates.stream().map(FrameState::symbol).collect(Collectors.joining(DELIMITER));
    }

    public boolean isMiss(FrameState frameState) {
        return frameState instanceof Miss;
    }
}
