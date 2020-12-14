package bowling.domain.frame;

import bowling.domain.Pitching;
import bowling.domain.pitchings.LastFramePitchings;

import java.util.List;

public class LastFrame extends Frame {
    private static final String NEXT_FRAME_INVOKE_ERR_MSG = "LastFrame은 NextFrame이 존재하지 않습니다.";
    private final int index;

    private LastFrame(int index) {
        super(LastFramePitchings.getInstance());
        this.index = index;
    }

    public static Frame getInstance(int index) {
        return new LastFrame(index);
    }

    @Override
    public Frame initNextFrame() {
        throw new IllegalStateException(NEXT_FRAME_INVOKE_ERR_MSG);
    }

    @Override
    public Pitching getNextPitching() {
        List<Pitching> value = getPitchings().getValue();
        if (value.isEmpty()) {
            return null;
        }
        return value.get(0);
    }

    @Override
    public Pitching getNextAndNextPitching() {
        List<Pitching> value = getPitchings().getValue();
        if (value.size() < 2) {
            return null;
        }
        return value.get(1);
    }

    @Override
    public Integer getScore() {
        List<Pitching> value = getPitchings().getValue();
        if ((value.contains(Pitching.STRIKE) || value.contains(Pitching.SPARE)) && value.size() < 3) {
            return null;
        }

        return value.stream()
                .mapToInt(Pitching::getScore)
                .sum();
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
