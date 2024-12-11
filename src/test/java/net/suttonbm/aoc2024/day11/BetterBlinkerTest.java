package net.suttonbm.aoc2024.day11;

import net.suttonbm.aoc2024.day11.service.BetterBlinker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BetterBlinkerTest {

    final BetterBlinker blinker = new BetterBlinker();

    @Test
    public void testRule1() {
        List<Long> input = List.of(0L);
        long output = blinker.blink(input, 1);

        assertThat(output).isEqualTo(1L);
    }

    @Test
    public void testRule2() {
        List<Long> input = List.of(4444L);
        long output = blinker.blink(input, 1);

        assertThat(output).isEqualTo(2L);
    }

    @Test
    public void testRule3() {
        List<Long> input = List.of(123L);
        long output = blinker.blink(input, 1);

        assertThat(output).isEqualTo(1L);
    }

    @Test
    public void testExampleOneBlink() {
        List<Long> input = List.of(125L, 17L);
        long output = blinker.blink(input, 1);
        assertThat(output).isEqualTo(3L);
    }

    @Test
    public void testExampleSixBlink() {
        List<Long> input = List.of(125L, 17L);
        long output = blinker.blink(input, 6);

        assertThat(output).isEqualTo(22L);
    }
}
