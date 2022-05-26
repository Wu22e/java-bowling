package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.BeforeProgress;
import bowling.domain.state.FirstBowl;
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

    public FinalFrame(LinkedList<FrameState> frameStates, int bowlCount) {
        // todo 검증!!
        // validateFrameStatesAndBowlCount(frameStates, bowlCount);
        this.frameStates = new LinkedList<>(List.of(new BeforeProgress()));
        this.bowlCount = bowlCount;
    }

    public static FinalFrame initialize() {
        return new FinalFrame();
    }

//    boolean isCurrentFrameBeforeProgressState() {
//        return frameStates.getLast() instanceof BeforeProgress;
//    }
//
//    boolean isCurrentFrameFirstBowlState() {
//        return frameStates.getLast() instanceof FirstBowl;
//    }

    boolean isEqualFrameStates(LinkedList<FrameState> frameStates) {
        return this.frameStates.equals(frameStates);
    }

    boolean isMatchBowlCount(int bowlCount) {
        return this.bowlCount == bowlCount;
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
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public String symbol() {
        return frameStates.stream().map(FrameState::symbol).collect(Collectors.joining(DELIMITER));
    }

    private boolean isMiss(FrameState frameState) {
        return frameState instanceof Miss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinalFrame that = (FinalFrame) o;

        if (bowlCount != that.bowlCount) return false;
        return frameStates.equals(that.frameStates);
    }

    @Override
    public int hashCode() {
        int result = frameStates.hashCode();
        result = 31 * result + bowlCount;
        return result;
    }
}
