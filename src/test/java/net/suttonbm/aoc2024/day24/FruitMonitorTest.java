package net.suttonbm.aoc2024.day24;

import net.suttonbm.aoc2024.day24.model.FruitMonitor;
import net.suttonbm.aoc2024.day24.service.FruitMonitorReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

import static org.assertj.core.api.Assertions.assertThat;

public class FruitMonitorTest {

    private final FruitMonitorReader reader = new FruitMonitorReader();

    @Test
    void smallTest() {
        FruitMonitor test = reader.load("24/smallTest.txt");
        test.run();

        assertThat(test.getWire("z00").hasFired()).isTrue();
        assertThat(test.getWire("z01").hasFired()).isTrue();
        assertThat(test.getWire("z02").hasFired()).isTrue();
        assertThat(test.getWire("z00").val()).isFalse();
        assertThat(test.getWire("z01").val()).isFalse();
        assertThat(test.getWire("z02").val()).isTrue();
    }

    @Test
    void bigTest() {
        FruitMonitor test = reader.load("24/test.txt");
        test.run();
        long result = test.getOutput();
        assertThat(result).isEqualTo(2024L);
    }

}