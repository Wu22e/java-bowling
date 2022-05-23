package bowling.domain;

public interface Frame {
    Frame bowl(Pins hitPins);

    boolean isFrameEnd();

    boolean isFinal();

    String symbol();
}
