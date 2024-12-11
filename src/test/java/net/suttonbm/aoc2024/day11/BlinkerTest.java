package net.suttonbm.aoc2024.day11;

import net.suttonbm.aoc2024.day11.service.Blinker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlinkerTest {

    private final Blinker blinker = new Blinker();

    @Test
    public void testRule1() {
        List<Long> input = List.of(0L);
        List<Long> output = blinker.blink(input);

        assertThat(output).isEqualTo(List.of(1L));
    }

    @Test
    public void testRule2() {
        List<Long> input = List.of(4444L);
        List<Long> output = blinker.blink(input);

        assertThat(output).isEqualTo(List.of(44L, 44L));
    }

    @Test
    public void testRule3() {
        List<Long> input = List.of(123L);
        List<Long> output = blinker.blink(input);

        assertThat(output).isEqualTo(List.of(248952L));
    }

    @Test
    public void testExampleOneBlink() {
        List<Long> input = List.of(125L, 17L);
        List<Long> output = blinker.blink(input);
        assertThat(output).isEqualTo(List.of(253000L, 1L, 7L));
    }

    @Test
    public void testExampleSixBlink() {
        List<Long> input = List.of(125L, 17L);
        List<Long> output = new ArrayList<>(input);
        for (int i=0; i<6; i++) {
            output = blinker.blink(output);
        }

        List<Long> expected = List.of(
                2097446912L, 14168L, 4048L, 2L, 0L, 2L, 4L, 40L, 48L, 2024L,
                40L, 48L, 80L, 96L, 2L, 8L, 6L, 7L, 6L, 0L, 3L, 2L
        );

        assertThat(output).isEqualTo(expected);
    }
}
