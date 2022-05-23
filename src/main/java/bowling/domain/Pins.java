package bowling.domain;

public class Pins {
    private static final int MIN_HIT_COUNT = 0;
    private static final int MAX_HIT_COUNT = 10;

    private final int hitPins;

    public Pins(int hitPins) {
        // todo : 인스턴스 캐싱 하기
        this.hitPins = hitPins;
    }

//    public Pins bowl(Pins pins) {
//        validateHitPins(hitPins);
//        return new Pins(this.hitPins + pins.hitPins);
//    }

    public int hitPins() {
        return this.hitPins;
    }

    public boolean isStrike() {
        return this.hitPins == MAX_HIT_COUNT;
    }

    public boolean isSpare(Pins nextHitPins) {
//        validateNextHitPins(nextHitPins);
        return this.hitPins + nextHitPins.hitPins == MAX_HIT_COUNT;
    }

    public boolean isGutter() {
        return this.hitPins == MIN_HIT_COUNT;
    }
}
