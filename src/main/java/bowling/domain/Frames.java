package bowling.domain;

import java.util.List;

public class Frames {
    private final static int MAX_FRAME_SIZE = 10;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
//        validateFrames(frames);
        this.frames = frames;
    }

    public static Frames initialize() {
        return new Frames(List.of(NormalFrame.initialize()));
    }

    public boolean isFinalFrameEnd() {
        return frames.size() == MAX_FRAME_SIZE && currentFrame().isFinal();
    }

    public void bowl(Pins hitPins) {
        Frame resultFrame = currentFrame().bowl(hitPins);
        if (resultFrame.isFrameEnd()) {
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