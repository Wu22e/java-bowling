package bowling.domain;

import bowling.domain.state.BeforeProgress;
import bowling.domain.state.FrameState;

public class NormalFrame implements Frame {
    private static final int START_FRAME_NUMBER = 0;

    private FrameState frameState;
    private final FrameNumber frameNumber;

    public NormalFrame(FrameState frameState, FrameNumber frameNumber) {
//        validate(frameState, frameNumber);
        this.frameState = frameState;
        this.frameNumber = frameNumber;
    }

    public static Frame initialize() {
        return new NormalFrame(new BeforeProgress(), new FrameNumber(START_FRAME_NUMBER));
    }

    @Override
    public Frame bowl(Pins hitPins) {
        this.frameState = frameState.bowl(hitPins);
        if (isFrameEnd()) {
            return nextFrame();
        }
        return this;
    }

    public Frame nextFrame() {
        if (frameNumber.next().isLast()) {
            return FinalFrame.initialize();
        }
        return NormalFrame.initialize();
    }

    @Override
    public boolean isFrameEnd() {
        return frameState.isFrameEnd();
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
