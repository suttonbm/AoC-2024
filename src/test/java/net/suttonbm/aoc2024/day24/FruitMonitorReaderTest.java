package net.suttonbm.aoc2024.day24;

import net.suttonbm.aoc2024.day24.model.FruitMonitor;
import net.suttonbm.aoc2024.day24.service.FruitMonitorReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FruitMonitorReaderTest {

    private final FruitMonitorReader reader = new FruitMonitorReader();

    @Test
    void testSmall() {
        FruitMonitor test = reader.load("24/smallTest.txt");
        assertThat(test.getWire("x00").hasFired()).isTrue();
        assertThat(test.getWire("x01").hasFired()).isTrue();
        assertThat(test.getWire("x02").hasFired()).isTrue();
        assertThat(test.getWire("y00").hasFired()).isTrue();
        assertThat(test.getWire("y01").hasFired()).isTrue();
        assertThat(test.getWire("y02").hasFired()).isTrue();

        assertThat(test.getWire("x00").val()).isTrue();
        assertThat(test.getWire("x01").val()).isTrue();
        assertThat(test.getWire("x02").val()).isTrue();
        assertThat(test.getWire("y00").val()).isFalse();
        assertThat(test.getWire("y01").val()).isTrue();
        assertThat(test.getWire("y02").val()).isFalse();

        assertThat(test.numGates()).isEqualTo(3);
    }
}