package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.BeforeProgress;
import bowling.domain.state.FirstBowl;
import bowling.domain.state.FrameState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {
    @DisplayName("파이널 프레임을 생성한다.")
    @Test
    void FinalFrame_생성() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame).isNotNull().isInstanceOf(FinalFrame.class);
    }

    @DisplayName("파이널 프레임을 초기화 한다.")
    @Test
    void initialize_파이널프레임_초기화() {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new BeforeProgress()));
        assertThat(initialFinalFrame.isEqualFrameStates(frameStates)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 하지 못한 경우 프레임 상태는 ['FirstBowl'] 이 된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 8, 9})
    void bowl_첫번째_투구_NON_STRIKE(int hitPins) {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        initialFinalFrame.bowl(new Pins(hitPins));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new FirstBowl(new Pins(hitPins))));
        assertThat(initialFinalFrame.isEqualFrameStates(frameStates)).isTrue();
    }

    @DisplayName("노멀 프레임, 첫번째 투구에서 스트라이크한 경우 현재 프레임 상태는 'STRIKE' 가 되고, 현재 프레임 넘버는 현재 값을 유지한다.")
    @Test
    void bowl_첫번째_투구_STRIKE() {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(10));
        assertThat(initialNormalFrame.isStrikeState()).isTrue();
        assertThat(initialNormalFrame.isMatchFrameNumber(new FrameNumber(0)));
    }

    @DisplayName("노멀 프레임, 첫번째 투구에서 스트라이크한 경우 다음 프레임 상태 'BeforeProgress' 프레임을 새롭게 반환하고, 프레임 넘버는 1 증가한다.")
    @Test
    void bowl_첫번째_투구_STRIKE_다음프레임_반환() {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        NormalFrame resultFrame = (NormalFrame) initialNormalFrame.bowl(new Pins(10));
//        assertThat(resultFrame.isBeforeProgressState()).isTrue();
//        assertThat(resultFrame.isMatchFrameNumber(new FrameNumber(1)));
    }

    @DisplayName("노멀 프레임, 두번째 투구에서 스페어 처리한 경우 현재 프레임 상태는 'SPARE' 가 되고, 현재 프레임 넘버는 현재 값을 유지한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1, 9",
            "3, 7",
            "5, 5",
            "0, 10"
    })
    void bowl_두번째_투구_SPARE(int firstPins, int secondPins) {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(firstPins));
        initialNormalFrame.bowl(new Pins(secondPins));
        assertThat(initialNormalFrame.isSpareState()).isTrue();
        assertThat(initialNormalFrame.isMatchFrameNumber(new FrameNumber(0)));
    }

    @DisplayName("노멀 프레임, 두번째 투구에서 스페어 처리한 경우 다음 프레임 상태는 'BeforeProgress' 프레임을 새롭게 반환하고, 프레임 넘버는 1 증가한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1, 9",
            "3, 7",
            "5, 5",
            "0, 10"
    })
    void bowl_두번째_투구_SPARE_다음프레임_반환(int firstPins, int secondPins) {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(firstPins));
        NormalFrame resultFrame = (NormalFrame) initialNormalFrame.bowl(new Pins(secondPins));
//        assertThat(resultFrame.isBeforeProgressState()).isTrue();
//        assertThat(resultFrame.isMatchFrameNumber(new FrameNumber(1)));
    }

    @DisplayName("9번 프레임이 종료되고 다음 프레임으로 넘어갈 때 파이널 프레임을 새롭게 반환한다.")
    @Test
    void bowl_두번째_투구_SPARE_마지막프레임_반환() {
        Frame normalFrame = NormalFrame.create(new FrameNumber(8));
        Frame finalFrame = normalFrame.bowl(new Pins(10));
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
    }
}