package bowling.domain.state;

import bowling.domain.Pins;

public class Miss implements FrameState {
    private static final String SYMBOL_FORMAT = "%s|%s";
    private static final String GUTTER_SYMBOL = "-";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        validateFirstPinsAndSecondPins(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validateFirstPinsAndSecondPins(Pins firstPins, Pins secondPins) {
        if (!firstPins.isMiss(secondPins)) {
            new IllegalArgumentException(String.format("Miss 상태는 첫번쨰 투구와 두번쨰 투구로 쓰러트린 핀 합이 10개 미만 이어야 합니다. 전달 받은 쓰러뜨린 firstPins 갯수 : %s, secondPins 갯수 : %s", firstPins, secondPins));
        }
    }

    @Override
    public FrameState bowl(Pins hitPins) {
        throw new IllegalArgumentException("프레임의 투구가 완료된 상태에서 볼을 굴릴 수 없습니다.");
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public String symbol() {
        return String.format(SYMBOL_FORMAT, gutterOrValue(firstPins), gutterOrValue(secondPins));
    }

    private String gutterOrValue(Pins pins) {
        if (pins.isGutter()) {
            return GUTTER_SYMBOL;
        }
        return String.valueOf(pins.hitPins());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Miss miss = (Miss) o;

        if (!firstPins.equals(miss.firstPins)) return false;
        return secondPins.equals(miss.secondPins);
    }

    @Override
    public int hashCode() {
        int result = firstPins.hashCode();
        result = 31 * result + secondPins.hashCode();
        return result;
    }
}
