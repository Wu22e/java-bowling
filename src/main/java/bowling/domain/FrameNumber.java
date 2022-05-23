package bowling.domain;

public class FrameNumber {
    private static final int LAST_FRAME_NUMBER = 9;

    private final int number;

    public FrameNumber(int number) {
//        validateNumber(number);
        this.number = number;
    }

    public FrameNumber next() {
        return new FrameNumber(this.number + 1);
    }

    public boolean isLast() {
        return this.number == LAST_FRAME_NUMBER;
    }
}
