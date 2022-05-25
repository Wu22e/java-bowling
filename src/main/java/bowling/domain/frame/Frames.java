package bowling.domain.frame;

import bowling.domain.Pins;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final static int MAX_FRAME_SIZE = 10;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
//        validateFrames(frames);
        this.frames = frames;
    }

    public static Frames initialize() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initialize());
        return new Frames(frames);
    }

    public boolean isFinalFrameEnd() {
        return frames.size() == MAX_FRAME_SIZE && currentFrame().isFrameEnd();
    }

    public void bowl(Pins hitPins) {
        Frame currentFrame = currentFrame();
        Frame resultFrame = currentFrame().bowl(hitPins);
        if (currentFrame.isFrameEnd() && !currentFrame.isFinalFrame()) {
            frames.add(resultFrame);
        }
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }

    public Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> frames() {
        return frames;
    }
}